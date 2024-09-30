package soma.edupilms.web.client;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;
import soma.edupilms.classroom.account.models.ClassroomAccountResponse;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.classroom.models.ActionInitRequest;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomInfoResponse;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.MyClassroomResponse;
import soma.edupilms.progress.models.ActionChangeRequest;
import soma.edupilms.progress.service.models.ActionStatus;

@Component
public interface DbServerApiClient {

    @PostExchange("/v1/classroom")
    ClassroomResponse createClassroom(@RequestBody ClassroomCreateRequest createClassroomRequest);

    @GetExchange("/v1/classroom")
    List<MyClassroomResponse> getMyClassrooms(@RequestParam Long accountId);

    @PostExchange("/v1/classroom/action/init")
    Long initAllActionStatus(@RequestBody ActionInitRequest actionInitRequest);

    @PatchExchange("/v1/classroom/{classroomId}")
    ClassroomResponse changeClassroomName(@PathVariable Long classroomId, @RequestBody String classroomName);

    @PostExchange("/v1/classroom/account")
    ClassroomAccountResponse registerClassroomAccount(@RequestBody RegisterGuestRequest registerGuestRequest);

    @PostExchange("/v1/classroom/account/action")
    ActionStatus updateAction(@RequestBody ActionChangeRequest actionChangeRequest);

    @GetExchange("/v1/classroom/info")
    ClassroomInfoResponse getClassroomInfo(@RequestParam Long classroomId);
}
