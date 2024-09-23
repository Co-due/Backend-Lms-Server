package soma.edupilms.guest.models.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassroomAccountResponse {

    private final Long accountId;
    private final Long classroomId;
    private final ClassroomAccountRole role;

}