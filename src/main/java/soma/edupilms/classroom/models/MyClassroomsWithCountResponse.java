package soma.edupilms.classroom.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyClassroomsWithCountResponse {

    private int classroomCount;
    private List<MyClassroomResponse> classrooms;

    public MyClassroomsWithCountResponse(int classroomCount, List<MyClassroomResponse> classrooms) {
        this.classroomCount = classroomCount;
        this.classrooms = classrooms;
    }
}
