package soma.edupilms.classroom.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soma.edupilms.classroom.account.models.ClassroomAccountRole;

@Getter
@NoArgsConstructor
public class MyClassroomsResponse {

    private List<MyClassroomResponse> hosts = new ArrayList<>();
    private List<MyClassroomResponse> guests = new ArrayList<>();

    public MyClassroomsResponse(List<MyClassroomResponse> myClassrooms) {
        hosts = myClassrooms.stream()
            .filter(myClassroom -> myClassroom.getRole().equals(ClassroomAccountRole.HOST))
            .toList();
        guests = myClassrooms.stream()
            .filter(myClassroom -> myClassroom.getRole().equals(ClassroomAccountRole.GUEST))
            .toList();
    }

}
