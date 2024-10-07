package soma.edupilms.progress.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import soma.edupilms.progress.models.ActionChangeRequest;
import soma.edupilms.progress.service.emitters.SseEmitters;
import soma.edupilms.progress.service.models.ActionStatus;
import soma.edupilms.web.client.DbServerApiClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseService {

    private final DbServerApiClient dbServerApiClient;
    private final SseEmitters sseEmitters;
    private final RedisService redisService;

    private static final Long TIME_OUT = 60 * 1000L; // 1분

    public SseEmitter createOrGetConnection(String classroomId, Long accountId) {

        redisService.subscribe(classroomId);

        // sseEmitter 가져오기 없으면 만들어서 반환
        return sseEmitters.findSseEmitter(classroomId)
            .orElseGet(() -> createNewSseEmitter(classroomId, accountId));
    }

    public ActionStatus sendAction(ActionChangeRequest actionChangeRequest) {
        ActionStatus actionStatus = dbServerApiClient.updateAction(actionChangeRequest);
        redisService.publish(actionChangeRequest);
        return actionStatus;
    }

    public ActionStatus getAction(Long classroomId, Long accountId) {
        ActionStatus actionStatus = dbServerApiClient.getActionStatus(classroomId, accountId);
        if (actionStatus == ActionStatus.DEFAULT) {
            ActionChangeRequest actionChangeRequest = new ActionChangeRequest(classroomId, accountId, ActionStatus.ING);
            actionStatus = dbServerApiClient.updateAction(actionChangeRequest);

            sendAction(actionChangeRequest);
        }

        return actionStatus;
    }

    private SseEmitter createNewSseEmitter(String classroomId, Long accountId) {
        final SseEmitter sseEmitter = new SseEmitter(TIME_OUT);

        sseEmitters.create(classroomId, sseEmitter);
        sseEmitter.onTimeout(sseEmitter::complete);
        sseEmitter.onError(e -> sseEmitter.complete());

        sseEmitter.onCompletion(() -> {
            log.info("[Sse connection closes] classroomId = {}", classroomId);
            sseEmitters.delete(classroomId);
            redisService.removeSubscribe(classroomId);
        });

        try {
            sseEmitter.send(SseEmitter.event().name("action")
                .data(new ActionChangeRequest(Long.parseLong(classroomId), accountId, ActionStatus.DEFAULT)));
        } catch (IOException e) {
            sseEmitters.delete(classroomId);
        }
        return sseEmitter;
    }
}
