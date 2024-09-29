package soma.edupilms.classroom.account.exception;

import org.springframework.http.HttpStatus;
import soma.edupilms.web.exception.ErrorEnum;

public enum ClassroomAccountErrorEnum implements ErrorEnum {
    // 400
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "LM-400201", "잘못된 값이 들어왔습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String details;

    ClassroomAccountErrorEnum(HttpStatus status, String code, String details) {
        this.status = status;
        this.code = code;
        this.details = details;
    }


    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDetail() {
        return details;
    }

}
