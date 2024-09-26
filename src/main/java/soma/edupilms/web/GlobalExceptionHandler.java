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
        printErrorLog(exception);

        ErrorResponse errorResponse = exception.getResponseBodyAs(ErrorResponse.class);

        return ResponseEntity
            .status(exception.getStatusCode())
            .body(errorResponse);
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> handle5xxError(HttpServerErrorException exception) {
        printErrorLog(exception);

        ErrorResponse errorResponse = exception.getResponseBodyAs(ErrorResponse.class);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);
    }

    @ExceptionHandler(value = UserServerException.class)
    public ResponseEntity<ErrorResponse> handleUserServerException(UserServerException exception) {
        printErrorLog(exception);

        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(new ErrorResponse(errorCode.getCode(), errorCode.getDetail()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        printErrorLog(exception);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse("DB-400999", "예상하지 못한 에러가 발생했습니다."));
    }

    private void printErrorLog(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        String className = stackTrace[0].getClassName();
        String methodName = stackTrace[0].getMethodName();

        String exceptionMessage = exception.getMessage();

        log.info("Exception occurred in class = {}, method = {}, message = {}, exception class = {}",
            className, methodName, exceptionMessage, exception.getClass().getCanonicalName());
    }

}
