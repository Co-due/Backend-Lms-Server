package soma.edupilms.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomCreateResponse;
import soma.edupilms.classroom.models.MyClassroomsResponse;
import soma.edupilms.web.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomCreateResponse createClassroom(ClassroomCreateRequest createClassroomRequest) {
        return dbServerApiClient.createClassroom(createClassroomRequest);
    }

    public MyClassroomsResponse getMyClassrooms(Long accountId) {

        return dbServerApiClient.getMyClassrooms(accountId);
    }
}
