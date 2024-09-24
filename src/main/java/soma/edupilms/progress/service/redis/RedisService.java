package soma.edupilms.progress.service.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import soma.edupilms.progress.models.ActionRequest;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisMessageListenerContainer container;
    private final RedisSubscriber subscriber;
    private final RedisTemplate<String, Object> template;

    public static final String CHANNEL_PREFIX = "classroomId:";

    public void subscribe(String classroomId) {
        container.addMessageListener(subscriber, ChannelTopic.of(getChannelName(classroomId)));
    }

    public void publish(ActionRequest actionRequest) {
        template.convertAndSend(getChannelName(String.valueOf(actionRequest.getClassroomId())), actionRequest);
    }

    public void removeSubscribe(String classroomId) {
        container.removeMessageListener(subscriber, ChannelTopic.of(getChannelName(classroomId)));
    }

    private String getChannelName(String classroomId) {
        return CHANNEL_PREFIX + classroomId;
    }

}
