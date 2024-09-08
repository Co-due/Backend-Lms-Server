package soma.haeya.lms.classroom.exception;

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

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<DefaultErrorResponse> handle4xxError(
        HttpClientErrorException exception
    ) {
        DefaultErrorResponse errorResponse = exception.getResponseBodyAs(DefaultErrorResponse.class);

        return ResponseEntity
            .status(exception.getStatusCode())
            .body(errorResponse);
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<DefaultErrorResponse> handle5xxError(
        HttpServerErrorException exception
    ) {
        DefaultErrorResponse errorResponse = exception.getResponseBodyAs(DefaultErrorResponse.class);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);
    }

}
