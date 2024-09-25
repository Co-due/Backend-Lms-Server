package soma.edupilms.classroom.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyClassrooms {

    private int classroomCount;
    private List<MyClassroom> classrooms = new ArrayList<>();

    public MyClassrooms(List<MyClassroom> classrooms) {
        this.classroomCount = classrooms.size();
        this.classrooms = classrooms;
    }
}
