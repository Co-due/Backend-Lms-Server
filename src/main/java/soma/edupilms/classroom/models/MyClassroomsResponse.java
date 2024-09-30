package soma.edupilms.classroom.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.classroom.account.models.ClassroomAccountRole;

@Getter
@NoArgsConstructor
public class MyClassroomsResponse {

    private List<MyClassroomResponse> host = new ArrayList<>();
    private List<MyClassroomResponse> guest = new ArrayList<>();

    public MyClassroomsResponse(List<MyClassroomResponse> myClassrooms) {
        host = myClassrooms.stream()
            .filter(myClassroom -> myClassroom.getRole().equals(ClassroomAccountRole.HOST))
            .toList();
        guest = myClassrooms.stream()
            .filter(myClassroom -> myClassroom.getRole().equals(ClassroomAccountRole.GUEST))
            .toList();
    }

}
