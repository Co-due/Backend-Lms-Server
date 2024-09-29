package soma.edupilms.classroom.account.models;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomAccountResponse {

    private Long id;
    private Long classroomId;
    private ClassroomAccountRole role;
    private int actionStatus;
    private LocalDateTime createdAt;

}