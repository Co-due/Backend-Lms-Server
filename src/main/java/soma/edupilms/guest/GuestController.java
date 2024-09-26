package soma.edupilms.guest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soma.edupilms._config.advice.AccountId;
import soma.edupilms.guest.models.ClassroomAccountResponse;
import soma.edupilms.guest.models.RegisterGuestRequest;
import soma.edupilms.guest.service.GuestService;
import soma.edupilms.web.models.SuccessResponse;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping("/v1/guest")
    public ResponseEntity<SuccessResponse> registerGuest(
        @AccountId @RequestBody RegisterGuestRequest registerGuestRequest
    ) {
        ClassroomAccountResponse classroomAccountResponse = guestService.registerGuest(registerGuestRequest);

        return ResponseEntity.ok(SuccessResponse.withDetailAndResult("게스트 등록에 성공했습니다.", classroomAccountResponse));
    }
}
