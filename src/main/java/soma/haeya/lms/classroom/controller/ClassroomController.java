package soma.haeya.lms.classroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.lms._config.advice.AccountId;
import soma.haeya.lms.classroom.model.response.MyClassroomWithCountResponse;
import soma.haeya.lms.classroom.models.request.ClassroomCreateRequest;
import soma.haeya.lms.classroom.models.response.ClassroomResponse;
import soma.haeya.lms.classroom.service.ClassroomService;
import soma.haeya.lms.common.models.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<SuccessResponse<ClassroomResponse>> createClassroom(
        @AccountId @RequestBody ClassroomCreateRequest createClassroomRequest
    ) {
        ClassroomResponse classroomResponse = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok(new SuccessResponse<>(classroomResponse));
    }

    @GetMapping
    public ResponseEntity<MyClassroomWithCountResponse> getMyClassrooms(@RequestHeader("X-User-Id") Long userId) {
        MyClassroomWithCountResponse myClassrooms = classroomService.getMyClassrooms(userId);

        return ResponseEntity.ok(myClassrooms);
    }

}
