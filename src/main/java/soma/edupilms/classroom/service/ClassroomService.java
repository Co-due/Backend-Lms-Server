package soma.edupilms.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomCreateResponse;
import soma.edupilms.classroom.models.MyClassroomsWithCountResponse;
import soma.edupilms.web.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final DbServerApiClient dbServerApiClient;
    private final InviteLinkService inviteLinkService;

    public ClassroomCreateResponse createClassroom(ClassroomCreateRequest createClassroomRequest) {
        String inviteLink = inviteLinkService.makeLink();
        createClassroomRequest.createInviteLink(inviteLink);

        return dbServerApiClient.createClassroom(createClassroomRequest);
    }

    public MyClassroomsWithCountResponse getMyClassrooms(Long userId) {

        return dbServerApiClient.getMyClassrooms(userId);
    }
}
