package soma.haeya.lms.group.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import soma.haeya.lms.common.model.response.DefaultErrorResponse;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class DbServerExceptionHandler {

    private final ObjectMapper mapper;

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<DefaultErrorResponse> handle4xxError(
        HttpClientErrorException exception
    ) {
        String message = extractMessage(exception.getMessage());

        return ResponseEntity
            .status(exception.getStatusCode())
            .body(new DefaultErrorResponse(message));
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<DefaultErrorResponse> handle5xxError(
        HttpServerErrorException exception
    ) {
        String message = extractMessage(exception.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new DefaultErrorResponse(message));
    }

    public String extractMessage(String errorString) {
        try {
            int jsonStart = errorString.indexOf(": ") + 2;
            String jsonPart = errorString.substring(jsonStart);

            jsonPart = jsonPart.substring(1, jsonPart.length() - 1);

            JsonNode jsonNode = mapper.readTree(jsonPart);

            if (jsonNode.has("message")) {
                return jsonNode.get("message").asText();
            } else {
                return "Empty message";
            }

        } catch (Exception e) {
            return "Failed to extract message: " + e.getMessage();
        }
    }
}
