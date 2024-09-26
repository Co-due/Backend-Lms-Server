package soma.edupilms.web.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomCreateResponse;
import soma.edupilms.classroom.models.MyClassroomsResponse;
import soma.edupilms.guest.models.request.RegisterGuestRequest;
import soma.edupilms.guest.models.response.ClassroomAccountResponse;

@Component
@HttpExchange("/v1")
public interface DbServerApiClient {

    @PostExchange("/classroom")
    ClassroomCreateResponse createClassroom(@RequestBody ClassroomCreateRequest createClassroomRequest);

    @PostExchange("/classroom-account")
    ClassroomAccountResponse registerGuest(@RequestBody RegisterGuestRequest registerGuestRequest);

    @GetExchange("/classroom")
    MyClassroomsResponse getMyClassrooms(@RequestParam Long accountId);

}
