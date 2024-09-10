package soma.haeya.lms.classroom.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import soma.haeya.lms.classroom.model.request.ClassroomCreateRequest;

@Component
@HttpExchange("/v1/classroom")
public interface ClassroomApiClient {

    @PostExchange
    void createClassroom(@RequestBody ClassroomCreateRequest createClassroomRequest);

}
