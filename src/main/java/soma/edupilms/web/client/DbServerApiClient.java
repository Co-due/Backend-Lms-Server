package soma.edupilms.web.client;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;
import soma.edupilms.classroom.account.models.ClassroomAccountResponse;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.MyClassroomResponse;

@Component
@HttpExchange("/v1")
public interface DbServerApiClient {

    @PostExchange("/classroom")
    ClassroomResponse createClassroom(@RequestBody ClassroomCreateRequest createClassroomRequest);

    @GetExchange("/classroom")
    List<MyClassroomResponse> getMyClassrooms(@RequestParam Long accountId);

    @PatchExchange("/classroom/{classroomId}")
    ClassroomResponse changeClassroomName(@PathVariable Long classroomId, @RequestBody String classroomName);

    @PostExchange("/classroom/account")
    ClassroomAccountResponse registerClassroomAccount(@RequestBody RegisterGuestRequest registerGuestRequest);

}
