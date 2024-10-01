package soma.edupilms.classroom.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.models.ActionInitRequest;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomInfoResponse;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.MyClassroomResponse;
import soma.edupilms.classroom.models.MyClassroomsResponse;
import soma.edupilms.web.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomResponse createClassroom(ClassroomCreateRequest createClassroomRequest) {
        return dbServerApiClient.createClassroom(createClassroomRequest);
    }

    public MyClassroomsResponse getMyClassrooms(Long accountId) {
        List<MyClassroomResponse> myClassrooms = dbServerApiClient.getMyClassrooms(accountId);

        return new MyClassroomsResponse(myClassrooms);
    }

    public void deleteClassroom(Long classroomId) {
        dbServerApiClient.deleteClassroom(classroomId);
    }

    public Long initAllActionsInClassroom(ActionInitRequest actionInitRequest) {
        return dbServerApiClient.initAllActionStatus(actionInitRequest);
    }

    public ClassroomResponse updateClassroomName(Long classroomId, String classroomName) {
        return dbServerApiClient.changeClassroomName(classroomId, classroomName);
    }

    public ClassroomInfoResponse getClassroomInfo(Long classroomId) {
        return dbServerApiClient.getClassroomInfo(classroomId);
    }
}
