package soma.edupilms.classroom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.classroom.account.models.ClassroomAccountRole;

@Getter
@NoArgsConstructor
public class MyClassroomResponse {

    private Long id;
    private String name;
    private ClassroomAccountRole role;
    private Long totalPeople;

    public MyClassroomResponse(Long id, String name, ClassroomAccountRole role, Long totalPeople) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.totalPeople = totalPeople;
    }
}
