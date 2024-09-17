package soma.haeya.lms.classroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.lms.classroom.model.request.ClassroomCreateRequest;
import soma.haeya.lms.classroom.service.ClassroomService;
import soma.haeya.lms.common.config.advice.AccountId;
import soma.haeya.lms.common.model.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createClassroom(
        @AccountId @RequestBody ClassroomCreateRequest createClassroomRequest
    ) {
        classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok(new SuccessResponse("성공적으로 클래스룸이 생성되었습니다."));
    }

}
