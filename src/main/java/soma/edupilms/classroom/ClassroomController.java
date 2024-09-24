package soma.edupilms.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupilms._config.advice.AccountId;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomCreateResponse;
import soma.edupilms.classroom.models.MyClassroomsResponse;
import soma.edupilms.classroom.service.ClassroomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<ClassroomCreateResponse> createClassroom(
        @AccountId @RequestBody ClassroomCreateRequest createClassroomRequest
    ) {
        ClassroomCreateResponse classroomResponse = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok(classroomResponse);
    }

    @GetMapping
    public ResponseEntity<MyClassroomsResponse> getMyClassrooms(@RequestHeader("X-Account-Id") Long accountId) {
        MyClassroomsResponse myClassrooms = classroomService.getMyClassrooms(accountId);

        return ResponseEntity.ok(myClassrooms);
    }

}
