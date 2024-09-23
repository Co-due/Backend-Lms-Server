package soma.edupilms.progress;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import soma.edupilms.progress.models.ActionRequest;
import soma.edupilms.progress.service.PublishService;
import soma.edupilms.progress.service.SubscribeService;

@RestController
@RequestMapping("/v1/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final SubscribeService subscribeService;
    private final PublishService publishService;

    @GetMapping(value = "/connect/{classroomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable String classroomId) {
        SseEmitter connection = subscribeService.subscribe(classroomId);

        return ResponseEntity.ok(connection);
    }

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody ActionRequest actionRequest) {
        publishService.publish(actionRequest);

        return ResponseEntity.noContent().build();
    }

}
