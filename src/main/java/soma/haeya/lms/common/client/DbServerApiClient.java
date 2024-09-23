package soma.haeya.lms.common.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import soma.haeya.lms.classroom.model.response.MyClassroomWithCountResponse;
import soma.haeya.lms.classroom.models.request.ClassroomCreateRequest;
import soma.haeya.lms.classroom.models.response.ClassroomResponse;
import soma.haeya.lms.guest.models.request.RegisterGuestRequest;
import soma.haeya.lms.guest.models.response.ClassroomAccountResponse;

@Component
@HttpExchange("/v1")
public interface DbServerApiClient {

    @PostExchange("/classroom")
    ClassroomResponse createClassroom(@RequestBody ClassroomCreateRequest createClassroomRequest);

    @PostExchange("/classroom-account")
    ClassroomAccountResponse registerGuest(@RequestBody RegisterGuestRequest registerGuestRequest);

    @GetExchange("/classroom")
    MyClassroomWithCountResponse getMyClassrooms(@RequestParam Long userId);

}
