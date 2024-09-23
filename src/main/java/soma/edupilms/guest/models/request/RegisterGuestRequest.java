package soma.edupilms.guest.models.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterGuestRequest {

    @NotNull
    private final Long classroomId;
    @NotNull
    private final Long accountId;

}
