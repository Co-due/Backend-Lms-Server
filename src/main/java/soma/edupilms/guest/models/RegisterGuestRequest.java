package soma.edupilms.guest.models;

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

}
