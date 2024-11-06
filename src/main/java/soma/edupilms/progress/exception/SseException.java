package soma.edupilms.progress.exception;


import soma.edupilms.web.exception.BaseException;
import soma.edupilms.web.exception.ErrorEnum;

public class SseException extends BaseException {

    public SseException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
