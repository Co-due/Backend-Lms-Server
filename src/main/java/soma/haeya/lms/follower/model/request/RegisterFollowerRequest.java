package soma.haeya.lms.follower.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterFollowerRequest {

    @NotNull
    private final Long classroomId;
    @NotNull
    private final Long userId;

}
