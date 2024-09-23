package soma.edupilms.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomCreateRequest {

    private Long accountId;
    private String name;
    private String inviteLink;

    public void createInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

}
