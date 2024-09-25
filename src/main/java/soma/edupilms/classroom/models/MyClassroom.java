package soma.edupilms.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.guest.models.response.ClassroomAccountRole;

@Getter
@NoArgsConstructor
public class MyClassroom {

    private Long id;
    private String name;
    private ClassroomAccountRole role;
    private Long totalPeople;

    public MyClassroom(Long id, String name, ClassroomAccountRole role, Long totalPeople) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.totalPeople = totalPeople;
    }
}
