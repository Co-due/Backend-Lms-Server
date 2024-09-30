package soma.edupilms.classroom.account.service.models;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.classroom.account.models.ClassroomAccountRole;

@Getter
@NoArgsConstructor
public class ClassroomAccountResponse {

    private Long id;
    private Long classroomId;
    private int actionStatus;
    private ClassroomAccountRole role;
    private LocalDateTime createdAt;


}