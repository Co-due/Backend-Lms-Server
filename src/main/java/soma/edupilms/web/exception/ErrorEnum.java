package soma.edupilms.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {
    //400
    //Classroom
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "LM-400001", "Invalid value provided."),
    CLASSROOM_NOT_FOUND(HttpStatus.BAD_REQUEST, "LM-400002", "The classroom does not exist."),
    CLASSROOM_NAME_DUPLICATE(HttpStatus.BAD_REQUEST, "LM-400003", "The classroom name already exists."),
    RESOURCE_ACCESS_EXCEPTION(HttpStatus.BAD_REQUEST, "LM-400004",
        "Unable to get resources due to network issues. check the server is started"),
    EMAIL_NOT_MATCH(HttpStatus.BAD_REQUEST, "LM-400005", "The member does not exist."),
    ALREADY_REGISTER(HttpStatus.BAD_REQUEST, "LM-400006", "You are already enrolled in the classroom."),
    CLASSROOM_ACCOUNT_NOT_FOUND(HttpStatus.BAD_REQUEST, "LM-400007", "The classroom account could not be found."),
    HOST_CAN_NOT_UPDATE_ACTION_STATUS(HttpStatus.BAD_REQUEST, "LM-400008",
        "The creator of the classroom cannot modify actions"),

    TASK_FAIL(HttpStatus.BAD_REQUEST, "LM-400999", "Please try again in a moment."),

    //500
    META_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "LM-500001", "Meta server Exception"),
    NOT_MATCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "LM-500002", "No matching error found.");

    private final HttpStatus status;
    private final String code;
    private final String details;

    ErrorEnum(HttpStatus status, String code, String details) {
        this.status = status;
        this.code = code;
        this.details = details;
    }
}
