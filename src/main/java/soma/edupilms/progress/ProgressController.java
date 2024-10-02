package soma.edupilms.progress;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import soma.edupilms._config.advice.AccountId;
import soma.edupilms.progress.models.ActionChangeRequest;
import soma.edupilms.progress.service.SseService;
import soma.edupilms.progress.service.models.ActionStatus;
import soma.edupilms.web.models.SuccessResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProgressController {

    private final SseService sseService;

    @GetMapping("/v1/guest/action/status")
    public ResponseEntity<SuccessResponse> guestClassroomAccount(
        @RequestParam Long classroomId,
        @RequestHeader("X-Account-Id") Long accountId
    ) {
        ActionStatus actionStatus = sseService.getAction(classroomId, accountId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("Success find guest action", actionStatus));
    }

    @GetMapping(value = "/v1/progress/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@RequestParam String classroomId,
        @RequestHeader("X-Account-Id") Long accountId) {
        log.info("[Sse connection]classroomId = {}", classroomId);
        SseEmitter connection = sseService.createOrGetConnection(classroomId, accountId);

        return ResponseEntity.ok(connection);
    }

    @PostMapping("/v1/progress/send")
    public ResponseEntity<SuccessResponse> send(@AccountId @RequestBody ActionChangeRequest actionChangeRequest) {
        log.info("[Sse send]channel = {}, actionType() = {}",
            actionChangeRequest.getClassroomId(),
            actionChangeRequest.getAction()
        );
        ActionStatus actionStatus = sseService.sendAction(actionChangeRequest);

        return ResponseEntity.ok(SuccessResponse.withDetailAndResult("Success change guest action", actionStatus));

    }

}
