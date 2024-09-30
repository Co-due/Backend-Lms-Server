package soma.edupilms.classroom.account;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soma.edupilms.classroom.account.models.ClassroomAccountResponse;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.classroom.account.service.ClassroomAccountService;
import soma.edupilms.web.models.SuccessResponse;

@RestController
@RequiredArgsConstructor
public class ClassroomAccountController {

    private final ClassroomAccountService classroomAccountService;

    @PostMapping("/v1/classroom/account")
    public ResponseEntity<SuccessResponse> registerClassroomAccount(
        @RequestBody RegisterGuestRequest registerGuestRequest
    ) {
        ClassroomAccountResponse classroomAccountResponse = classroomAccountService.registerClassroomAccount(
            registerGuestRequest);

        return ResponseEntity.ok(
            SuccessResponse.withDetailAndResult("success create classroom account", classroomAccountResponse));
    }

    @GetMapping("/v1/classroom/account")
    public ResponseEntity<SuccessResponse> getClassroomAccount(@RequestParam Long classroomId) {
        List<ClassroomAccountResponse> classroomAccountResponses =
            classroomAccountService.getAllClassroomAccounts(classroomId);

        return ResponseEntity.ok(
            SuccessResponse.withDetailAndResult(
                "success retrieved classroom account by classroomId",
                classroomAccountResponses
            ));
    }
}
