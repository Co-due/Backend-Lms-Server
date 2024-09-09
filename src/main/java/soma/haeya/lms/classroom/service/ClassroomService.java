package soma.haeya.lms.classroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.haeya.lms.classroom.model.request.ClassroomCreateRequest;
import soma.haeya.lms.common.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final DbServerApiClient dbServerApiClient;
    private final InviteLinkService inviteLinkService;

    public void createClassroom(ClassroomCreateRequest createClassroomRequest) {
        String inviteLink = inviteLinkService.makeLink();
        createClassroomRequest.createInviteLink(inviteLink);

        dbServerApiClient.createClassroom(createClassroomRequest);
    }
}
