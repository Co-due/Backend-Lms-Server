package soma.haeya.lms.group.model.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassroomCreateRequest {

    private final Long userId;
    private final String name;
    private String inviteLink;

    public void createInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

}
