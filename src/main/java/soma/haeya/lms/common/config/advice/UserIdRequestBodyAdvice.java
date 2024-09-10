package soma.haeya.lms.common.config.advice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class UserIdRequestBodyAdvice implements RequestBodyAdvice {

    private final ObjectMapper mapper;
    private static final String USER_ID_FIELD_NAME = "userId";

    @Override
    public boolean supports(MethodParameter methodParameter, @NonNull Type targetType,
        @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    @NonNull
    public HttpInputMessage beforeBodyRead(
        HttpInputMessage inputMessage,
        @NonNull MethodParameter parameter,
        @NonNull Type targetType,
        @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) throws IOException {
        // header 꺼내오기
        String userIdHeader = inputMessage.getHeaders().getFirst("X-User-Id");
        if (userIdHeader == null) {
            throw new IllegalArgumentException("X-User-Id header가 없습니다");
        }
        Long userId = Long.parseLong(userIdHeader);

        // 매핑 클래스에 userId 필드가 있는지 확인
        Class<?> targetClass = (Class<?>) targetType;
        boolean hasUserIdField = Arrays.stream(targetClass.getDeclaredFields())
            .anyMatch(field -> field.getName().equals(USER_ID_FIELD_NAME));

        if (!hasUserIdField) {
            return inputMessage;
        }

        // body에 userId 추가
        JsonNode bodyNode = mapper.readTree(inputMessage.getBody());

        if (bodyNode instanceof ObjectNode) {
            ((ObjectNode) bodyNode).put(USER_ID_FIELD_NAME, userId);
        }

        // 4. 수정된 body와 함께 새로운 HttpInputMessage 반환
        InputStream modifiedBodyStream = new ByteArrayInputStream(mapper.writeValueAsBytes(bodyNode));

        return new CustomHttpInputMessage(inputMessage.getHeaders(), modifiedBodyStream);
    }

    @Override
    @NonNull
    public Object afterBodyRead(
        @NonNull Object body,
        @NonNull HttpInputMessage inputMessage,
        @NonNull MethodParameter parameter,
        @NonNull Type targetType,
        @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return body;
    }

    @Override
    public Object handleEmptyBody(
        Object body,
        @NonNull HttpInputMessage inputMessage,
        @NonNull MethodParameter parameter,
        @NonNull Type targetType,
        @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return body;
    }
}
