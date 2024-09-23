package soma.haeya.lms.classroom.models.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassroomCreateRequest {

    private final Long accountId;
    private final String name;
    private String inviteLink;

    public void createInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

}
