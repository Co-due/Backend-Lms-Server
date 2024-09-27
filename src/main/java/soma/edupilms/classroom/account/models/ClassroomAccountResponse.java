package soma.edupilms.classroom.account.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomAccountResponse {

    private Long accountId;
    private Long classroomId;
    private ClassroomAccountRole role;

}