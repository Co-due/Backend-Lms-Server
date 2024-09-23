package soma.haeya.lms.progress.service.redis;


import static soma.haeya.lms.progress.service.redis.RedisService.CHANNEL_PREFIX;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import soma.haeya.lms.progress.models.ActionRequest;
import soma.haeya.lms.progress.service.sse.SseEmitterService;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final SseEmitterService sseEmitterService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String channel = new String(message.getChannel())
                .substring(CHANNEL_PREFIX.length());

            ActionRequest actionRequest = objectMapper.readValue(
                message.getBody(),
                ActionRequest.class
            );

            // 클라이언트에게 event 데이터 전송
            sseEmitterService.sendAction(channel, actionRequest);
        } catch (IOException e) {
            log.error("IOException is occurred. ", e);
        }
    }
}
