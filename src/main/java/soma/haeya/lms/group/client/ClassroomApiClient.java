package soma.haeya.lms.group.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import soma.haeya.lms.group.model.request.CreateClassroomRequest;

@Component
@HttpExchange("/v1/classroom")
public interface ClassroomApiClient {

    @PostExchange
    void createClassroom(@RequestBody CreateClassroomRequest createClassroomRequest);

}
