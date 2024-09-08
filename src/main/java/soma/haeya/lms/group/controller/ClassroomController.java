package soma.haeya.lms.group.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.lms.group.model.request.CreateClassroomRequest;
import soma.haeya.lms.group.service.ClassroomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<Void> createClassroom(CreateClassroomRequest createClassroomRequest) {
        classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity.ok().build();
    }

}
