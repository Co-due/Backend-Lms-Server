package soma.edupilms.progress;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import soma.edupilms._config.advice.AccountId;
import soma.edupilms.progress.models.ActionChangeRequest;
import soma.edupilms.progress.service.SseService;
import soma.edupilms.progress.service.models.ActionStatus;
import soma.edupilms.web.models.SuccessResponse;

@RestController
@RequiredArgsConstructor
public class ProgressController {

    private final SseService sseService;

    @GetMapping(value = "/v1/progress/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@RequestParam String classroomId) {
        System.out.println("classroomId = " + classroomId);
        SseEmitter connection = sseService.createOrGetConnection(classroomId);

        return ResponseEntity.ok(connection);
    }

    @PostMapping("/v1/progress/send")
    public ResponseEntity<SuccessResponse> send(@AccountId @RequestBody ActionChangeRequest actionChangeRequest) {
        System.out.println("actionRequest.getActionType() = " + actionChangeRequest.getAction());
        ActionStatus actionStatus = sseService.sendAction(actionChangeRequest);

        return ResponseEntity.ok(SuccessResponse.withDetailAndResult("액션이 변경되었습니다.", actionStatus));

    }

}
