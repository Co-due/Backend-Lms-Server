package soma.edupilms.classroom.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soma.edupilms._config.advice.AccountId;
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
}
