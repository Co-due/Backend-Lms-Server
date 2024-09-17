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
public class AccountIdRequestBodyAdvice implements RequestBodyAdvice {

    private final ObjectMapper mapper;
    private static final String USER_ID_FIELD_NAME = "accountId";

    @Override
    public boolean supports(MethodParameter methodParameter, @NonNull Type targetType,
        @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(AccountId.class);
    }

    @Override
    @NonNull
    public HttpInputMessage beforeBodyRead(
        @NonNull HttpInputMessage inputMessage,
        @NonNull MethodParameter parameter,
        @NonNull Type targetType,
        @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) throws IOException {
        // header에서 accountId 꺼내오기
        Long accountId = extractAccountIdFromHeader(inputMessage);

        // 매핑 클래스에 accountId 필드가 없으면 기존 요청 반환
        if (!checkAccountIdFieldInTargetClass(targetType)) {
            return inputMessage;
        }

        // body에 accountId 추가
        JsonNode bodyNode = mapper.readTree(inputMessage.getBody());

        if (bodyNode instanceof ObjectNode) {
            ((ObjectNode) bodyNode).put(USER_ID_FIELD_NAME, accountId);
        }

        // 4. 수정된 body와 함께 새로운 HttpInputMessage 반환
        InputStream modifiedBodyStream = new ByteArrayInputStream(mapper.writeValueAsBytes(bodyNode));

        return new CustomHttpInputMessage(inputMessage.getHeaders(), modifiedBodyStream);
    }

    private Long extractAccountIdFromHeader(HttpInputMessage inputMessage) {
        String accountIdHeader = inputMessage.getHeaders().getFirst("X-Account-Id");
        if (accountIdHeader == null) {
            throw new IllegalArgumentException("X-Account-Id header가 없습니다");
        }
        return Long.parseLong(accountIdHeader);
    }

    private boolean checkAccountIdFieldInTargetClass(Type targetType) {
        Class<?> targetClass = (Class<?>) targetType;

        return Arrays.stream(targetClass.getDeclaredFields())
            .anyMatch(field -> field.getName().equals(USER_ID_FIELD_NAME));
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
