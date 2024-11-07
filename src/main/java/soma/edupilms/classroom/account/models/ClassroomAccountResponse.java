package soma.edupilms.classroom.account.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.progress.service.models.ActionStatus;

@Getter
@NoArgsConstructor
public class ClassroomAccountResponse {

    private Long id;
    private String email;
    private String name;
    private ActionStatus status;
    private ClassroomAccountRole role;

}