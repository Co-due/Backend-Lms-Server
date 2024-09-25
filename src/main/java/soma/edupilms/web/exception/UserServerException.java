package soma.edupilms.web.exception;

import lombok.Getter;

@Getter
public class UserServerException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserServerException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }
}
