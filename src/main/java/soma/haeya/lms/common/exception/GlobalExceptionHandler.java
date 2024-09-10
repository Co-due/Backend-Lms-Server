package soma.haeya.lms.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import soma.haeya.lms.common.model.response.ErrorResponse;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handle4xxError(HttpClientErrorException exception) {
        ErrorResponse errorResponse = exception.getResponseBodyAs(ErrorResponse.class);

        return ResponseEntity
            .status(exception.getStatusCode())
            .body(errorResponse);
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> handle5xxError(HttpServerErrorException exception) {
        ErrorResponse errorResponse = exception.getResponseBodyAs(ErrorResponse.class);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);
    }

    @ExceptionHandler(value = UserServerException.class)
    public ResponseEntity<ErrorResponse> handleUserServerException(UserServerException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(errorResponse);
    }

}
