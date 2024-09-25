package soma.edupilms.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import soma.edupilms.web.exception.ErrorCode;
import soma.edupilms.web.exception.UserServerException;
import soma.edupilms.web.models.ErrorResponse;

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
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(new ErrorResponse(errorCode.getCode(), errorCode.getDetail()));
    }

}
