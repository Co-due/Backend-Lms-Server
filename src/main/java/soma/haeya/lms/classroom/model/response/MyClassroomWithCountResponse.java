package soma.haeya.lms.classroom.model.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyClassroomWithCountResponse {

    private final int classroomCount;
    private final List<ClassroomResponse> classrooms;

}
