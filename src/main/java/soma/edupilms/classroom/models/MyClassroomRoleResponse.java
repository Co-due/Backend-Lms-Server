package soma.edupilms.classroom.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.classroom.account.models.ClassroomAccountRole;

@Getter
@NoArgsConstructor
public class MyClassroomRoleResponse {

    private MyClassrooms host = new MyClassrooms();
    private MyClassrooms guest = new MyClassrooms();

    public MyClassroomRoleResponse(List<MyClassroom> myClassrooms) {
        host = new MyClassrooms(
            myClassrooms.stream()
                .filter(myClassroom -> myClassroom.getRole().equals(ClassroomAccountRole.HOST))
                .toList()
        );
        guest = new MyClassrooms(
            myClassrooms.stream()
                .filter(myClassroom -> myClassroom.getRole().equals(ClassroomAccountRole.GUEST))
                .toList()
        );
    }

}
