package soma.edupilms.progress.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.progress.service.models.ActionType;

@Getter
@NoArgsConstructor
public class ActionRequest {

    private Long classroomId;
    private Long classroomAccountId;
    private ActionType actionType;

}
