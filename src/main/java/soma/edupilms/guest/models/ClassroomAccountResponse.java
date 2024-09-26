package soma.edupilms.guest.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomAccountResponse {

    private Long accountId;
    private Long classroomId;
    private ClassroomAccountRole role;

}