package soma.edupilms.web.exception;

public class MetaServerException extends BaseException {

    public MetaServerException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
