package soma.edupilms.progress.service.sse;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import soma.edupilms.progress.models.ActionRequest;
import soma.edupilms.progress.service.redis.RedisService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseEmitterService {

    private final SseEmitterRepository sseEmitterRepository;

    private static final Long TIME_OUT = 60 * 1000L; // 1분

    public SseEmitter createOrGetConnection(RedisService redisService, String classroomId) {
        // sseEmitter 가져오기 없으면 만들어서 반환
        return sseEmitterRepository.findSseEmitter(classroomId)
            .orElseGet(() -> createNewSseEmitter(redisService, classroomId));
    }

    public void sendAction(String classroomId, ActionRequest actionRequest) {
        sseEmitterRepository.findSseEmitter(classroomId).ifPresent(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event().name("action").data(actionRequest));
            } catch (IOException e) {
                sseEmitterRepository.delete(classroomId);
            }
        });
    }

    private SseEmitter createNewSseEmitter(RedisService redisService, String classroomId) {
        final SseEmitter sseEmitter = new SseEmitter(TIME_OUT);

        sseEmitterRepository.create(classroomId, sseEmitter);
        sseEmitter.onTimeout(sseEmitter::complete);
        sseEmitter.onError(e -> sseEmitter.complete());

        sseEmitter.onCompletion(() -> {
            sseEmitterRepository.delete(classroomId);
            redisService.removeSubscribe(classroomId);
        });

        return sseEmitter;
    }
}
