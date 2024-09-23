package soma.edupilms.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyClassroomResponse {

    private Long classroomId;
    private String name;
    private Long guestCount;

    public MyClassroomResponse(Long classroomId, String name, Long guestCount) {
        this.classroomId = classroomId;
        this.name = name;
        this.guestCount = guestCount;
    }
}
