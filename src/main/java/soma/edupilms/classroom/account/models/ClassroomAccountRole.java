package soma.edupilms.classroom.account.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import soma.edupilms.classroom.account.exception.ClassroomAccountException;
import soma.edupilms.web.exception.ErrorEnum;

@Getter
public enum ClassroomAccountRole {
    HOST(1), GUEST(2);

    private final int value;

    ClassroomAccountRole(int value) {
        this.value = value;
    }

    @JsonCreator
    public static ClassroomAccountRole fromValue(int value) {
        for (ClassroomAccountRole role : ClassroomAccountRole.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        throw new ClassroomAccountException(ErrorEnum.INVALID_VALUE);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

}
