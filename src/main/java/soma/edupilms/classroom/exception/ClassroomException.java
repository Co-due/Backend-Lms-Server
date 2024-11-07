package soma.edupilms.classroom.exception;


import soma.edupilms.web.exception.BaseException;
import soma.edupilms.web.exception.ErrorEnum;

public class ClassroomException extends BaseException {

    public ClassroomException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
