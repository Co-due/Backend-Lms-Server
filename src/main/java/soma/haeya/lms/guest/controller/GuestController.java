package soma.haeya.lms.guest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.lms.common.config.advice.AccountId;
import soma.haeya.lms.common.models.response.SuccessResponse;
import soma.haeya.lms.guest.models.request.RegisterGuestRequest;
import soma.haeya.lms.guest.models.response.ClassroomAccountResponse;
import soma.haeya.lms.guest.service.GuestService;

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
