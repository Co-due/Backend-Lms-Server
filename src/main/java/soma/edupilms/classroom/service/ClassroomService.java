package soma.edupilms.classroom.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.MyClassroom;
import soma.edupilms.classroom.models.MyClassroomRoleResponse;
import soma.edupilms.web.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomResponse createClassroom(ClassroomCreateRequest createClassroomRequest) {
        return dbServerApiClient.createClassroom(createClassroomRequest);
    }

    public MyClassroomRoleResponse getMyClassrooms(Long accountId) {
        List<MyClassroom> myClassrooms = dbServerApiClient.getMyClassrooms(accountId);

        return new MyClassroomRoleResponse(myClassrooms);
    }

    public ClassroomResponse updateClassroomName(Long classroomId, String classroomName) {
        return dbServerApiClient.changeClassroomName(classroomId, classroomName);
    }

}
