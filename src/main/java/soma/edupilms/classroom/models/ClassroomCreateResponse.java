package soma.edupilms.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomCreateResponse {

    private Long id;
    private String name;
    private String inviteLink;

}
