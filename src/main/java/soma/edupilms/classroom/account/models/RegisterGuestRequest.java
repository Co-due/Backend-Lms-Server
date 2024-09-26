package soma.edupilms.classroom.account.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterGuestRequest {

    @NotNull
    private Long classroomId;
    @NotNull
    private Long accountId;
    @NotNull
    private ClassroomAccountRole role;

}
