package soma.haeya.lms.guest.model.request;

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
