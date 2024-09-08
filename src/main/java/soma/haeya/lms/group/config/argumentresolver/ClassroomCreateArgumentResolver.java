package soma.haeya.lms.group.config.argumentresolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import soma.haeya.lms.group.model.request.ClassroomCreateRequest;

@Component
public class ClassroomCreateArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(ClassroomCreateRequest.class);
    }

    @Override
    public Object resolveArgument(
        @NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        Long userId = extractUserIdFromHeader(request);
        String name = extractNameFromBody(request);

        return new ClassroomCreateRequest(userId, name);
    }

    private Long extractUserIdFromHeader(HttpServletRequest request) {
        String userIdHeader = request.getHeader("X-User-Id");

        if (userIdHeader == null || userIdHeader.isEmpty()) {
            throw new IllegalArgumentException("X-User-Id 헤더가 없습니다.");
        }

        if (!userIdHeader.matches("[0-9]+")) {
            throw new IllegalArgumentException("X-User-Id 값이 유효한 숫자가 아닙니다.");
        }

        return Long.parseLong(userIdHeader);
    }

    private String extractNameFromBody(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(request.getInputStream());
        return root.path("name").asText();
    }
}
