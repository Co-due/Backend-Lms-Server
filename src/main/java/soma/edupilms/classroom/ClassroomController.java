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
import soma.edupilms.classroom.models.MyClassroomsWithCountResponse;
import soma.edupilms.classroom.service.ClassroomService;
import soma.edupilms.web.models.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<SuccessResponse<ClassroomCreateResponse>> createClassroom(
        @AccountId @RequestBody ClassroomCreateRequest createClassroomRequest
    ) {
        ClassroomCreateResponse classroomResponse = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok(new SuccessResponse<>(classroomResponse));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<MyClassroomsWithCountResponse>> getMyClassrooms(
        @RequestHeader("X-User-Id") Long userId
    ) {
        MyClassroomsWithCountResponse myClassrooms = classroomService.getMyClassrooms(userId);

        return ResponseEntity.ok(new SuccessResponse<>(myClassrooms));
    }

}
