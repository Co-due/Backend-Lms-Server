package soma.edupilms.progress.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import soma.edupilms.progress.models.ActionRequest;
import soma.edupilms.progress.service.emitters.SseEmitters;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseService {

    private final SseEmitters sseEmitters;
    private final RedisService redisService;

    private static final Long TIME_OUT = 60 * 1000L; // 1분

    public SseEmitter createOrGetConnection(String classroomId) {

        redisService.subscribe(classroomId);

        // sseEmitter 가져오기 없으면 만들어서 반환
        return sseEmitters.findSseEmitter(classroomId)
            .orElseGet(() -> createNewSseEmitter(classroomId));
    }

    public void sendAction(ActionRequest actionRequest) {
        redisService.publish(actionRequest);
    }

    private SseEmitter createNewSseEmitter(String classroomId) {
        final SseEmitter sseEmitter = new SseEmitter(TIME_OUT);

        sseEmitters.create(classroomId, sseEmitter);
        sseEmitter.onTimeout(sseEmitter::complete);
        sseEmitter.onError(e -> sseEmitter.complete());

        sseEmitter.onCompletion(() -> {
            System.out.println("[Sse connection closes] classroomId = " + classroomId);
            sseEmitters.delete(classroomId);
            redisService.removeSubscribe(classroomId);
        });

        return sseEmitter;
    }
}
