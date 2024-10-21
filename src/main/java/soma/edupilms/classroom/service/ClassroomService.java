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
import soma.edupilms.web.client.MetaServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final MetaServerApiClient metaServerApiClient;

    public ClassroomResponse createClassroom(ClassroomCreateRequest createClassroomRequest) {
        return metaServerApiClient.createClassroom(createClassroomRequest);
    }

    public MyClassroomsResponse getMyClassrooms(Long accountId) {
        List<MyClassroomResponse> myClassrooms = metaServerApiClient.getMyClassrooms(accountId);

        return new MyClassroomsResponse(myClassrooms);
    }

    public void deleteClassroom(Long classroomId) {
        metaServerApiClient.deleteClassroom(classroomId);
    }

    public Long initAllActionsInClassroom(ActionInitRequest actionInitRequest) {
        return metaServerApiClient.initAllActionStatus(actionInitRequest);
    }

    public ClassroomResponse updateClassroomName(Long classroomId, String classroomName) {
        return metaServerApiClient.changeClassroomName(classroomId, classroomName);
    }

    public ClassroomInfoResponse getClassroomInfo(Long classroomId) {
        return metaServerApiClient.getClassroomInfo(classroomId);
    }
}
