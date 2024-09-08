package soma.haeya.lms.group.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.lms.common.model.response.DefaultSuccessResponse;
import soma.haeya.lms.group.model.request.ClassroomCreateRequest;
import soma.haeya.lms.group.service.ClassroomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<DefaultSuccessResponse> createClassroom(ClassroomCreateRequest createClassroomRequest) {
        classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok(new DefaultSuccessResponse("성공적으로 클래스룸이 생성되었습니다."));
    }

}
