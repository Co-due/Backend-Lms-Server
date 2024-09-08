package soma.haeya.lms.group.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.haeya.lms.group.client.ClassroomApiClient;
import soma.haeya.lms.group.model.request.ClassroomCreateRequest;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomApiClient classroomApiClient;
    private final InviteLinkService inviteLinkService;

    public void createClassroom(ClassroomCreateRequest createClassroomRequest) {
        String inviteLink = inviteLinkService.makeLink();
        createClassroomRequest.createInviteLink(inviteLink);

        classroomApiClient.createClassroom(createClassroomRequest);
    }
}
