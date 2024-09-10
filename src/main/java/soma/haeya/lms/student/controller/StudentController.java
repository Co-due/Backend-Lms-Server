package soma.haeya.lms.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.lms.common.config.argumentresolver.UserId;
import soma.haeya.lms.common.model.response.DefaultSuccessResponse;
import soma.haeya.lms.student.model.request.RegisterStudentRequest;
import soma.haeya.lms.student.service.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<DefaultSuccessResponse> registerStudent(
        @UserId @RequestBody RegisterStudentRequest registerStudentRequest
    ) {
        studentService.registerStudent(registerStudentRequest);

        return ResponseEntity.ok(new DefaultSuccessResponse("클래스룸에 참여했습니다."));
    }
}
