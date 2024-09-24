package soma.edupilms.guest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupilms._config.advice.AccountId;
import soma.edupilms.guest.models.request.RegisterGuestRequest;
import soma.edupilms.guest.models.response.ClassroomAccountResponse;
import soma.edupilms.guest.service.GuestService;
import soma.edupilms.web.models.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guest")
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<SuccessResponse<ClassroomAccountResponse>> registerGuest(
        @AccountId @RequestBody RegisterGuestRequest registerGuestRequest
    ) {
        ClassroomAccountResponse classroomAccountResponse = guestService.registerGuest(registerGuestRequest);

        return ResponseEntity.ok(new SuccessResponse<>(classroomAccountResponse));
    }
}
