package soma.haeya.lms.student.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterStudentRequest {

    @NotNull
    private final Long classroomId;
    @NotNull
    private final Long userId;

}
