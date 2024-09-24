package soma.edupilms.progress.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import soma.edupilms.progress.service.models.ActionType;

@Getter
@RequiredArgsConstructor
public class ActionRequest {

    private final Long classroomId;
    private final Long classroomAccountId;
    private final ActionType actionType;

}
