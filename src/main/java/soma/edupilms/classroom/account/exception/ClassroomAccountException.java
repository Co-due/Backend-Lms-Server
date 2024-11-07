package soma.edupilms.classroom.account.exception;


import soma.edupilms.web.exception.BaseException;
import soma.edupilms.web.exception.ErrorEnum;

public class ClassroomAccountException extends BaseException {

    public ClassroomAccountException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
