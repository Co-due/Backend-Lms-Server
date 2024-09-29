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
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.MyClassroomResponse;

@Component
public interface DbServerApiClient {

    @PostExchange("/v1/classroom")
    ClassroomResponse createClassroom(@RequestBody ClassroomCreateRequest createClassroomRequest);

    @GetExchange("/v1/classroom")
    List<MyClassroomResponse> getMyClassrooms(@RequestParam Long accountId);

    @PostExchange("/v1/classroom/action/initialization")
    Long initialization(@RequestBody ActionInitializeRequest actionInitializeRequest);

    @PatchExchange("/v1/classroom/{classroomId}")
    ClassroomResponse changeClassroomName(@PathVariable Long classroomId, @RequestBody String classroomName);

    @PostExchange("/v1/classroom/account")
    ClassroomAccountResponse registerClassroomAccount(@RequestBody RegisterGuestRequest registerGuestRequest);

    @PostExchange("/v1/classroom/account/action")
    ActionStatus updateAction(@RequestBody ActionChangeRequest actionChangeRequest);

}
