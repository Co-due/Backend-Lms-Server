package soma.edupilms.web;

import javax.security.auth.login.AccountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import soma.edupilms.classroom.account.exception.ClassroomAccountException;
import soma.edupilms.classroom.exception.ClassroomException;
import soma.edupilms.progress.exception.SseException;
import soma.edupilms.web.exception.BaseException;
import soma.edupilms.web.exception.ErrorEnum;
import soma.edupilms.web.exception.MetaServerException;
import soma.edupilms.web.models.ErrorResponse;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({
        AccountException.class,
        ClassroomException.class,
        ClassroomAccountException.class,
        SseException.class
    })
    public ResponseEntity<ErrorResponse> handelDomainException(BaseException exception) {
        printErrorLog(exception);

        ErrorEnum errorCode = exception.getErrorCode();

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(errorCode.getCode(), errorCode.getDetails()));
    }

    @ExceptionHandler(value = MetaServerException.class)
    public ResponseEntity<ErrorResponse> handleMetaServerException(MetaServerException exception) {
        log.error("[Meta Exception] code={}, message={}, detail message={}", exception.getErrorCode().getCode(),
            exception.getErrorCode().getDetails(),
            exception.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(ErrorEnum.TASK_FAIL.getCode(), ErrorEnum.TASK_FAIL.getDetails()));
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponse> handelBaseException(BaseException exception) {
        printErrorLog(exception);

        ErrorEnum errorCode = exception.getErrorCode();

        return ResponseEntity
            .status(errorCode.getStatus())
            .body(new ErrorResponse(errorCode.getCode(), errorCode.getDetails()));
    }

    @ExceptionHandler(value = ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException exception) {
        printErrorLog(exception);

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("LM-404999", "Cannot access a specific API."));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        printErrorLog(exception);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ErrorResponse("LM-400999", "Unexpected error has occurred."));
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
