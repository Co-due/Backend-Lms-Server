package soma.haeya.lms.classroom.models.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClassroomResponse {

    private final Long id;
    private final String name;
    private final String inviteLink;
}
