package soma.haeya.lms.follower.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.lms.common.config.advice.UserId;
import soma.haeya.lms.common.model.response.SuccessResponse;
import soma.haeya.lms.follower.model.request.RegisterFollowerRequest;
import soma.haeya.lms.follower.service.FollowerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/follower")
public class FollowerController {

    private final FollowerService followerService;

    @PostMapping
    public ResponseEntity<SuccessResponse> registerFollower(
        @UserId @RequestBody RegisterFollowerRequest registerFollowerRequest
    ) {
        followerService.registerFollower(registerFollowerRequest);

        return ResponseEntity.ok(new SuccessResponse("클래스룸에 참여했습니다."));
    }
}
