package soma.edupilms.progress.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.progress.models.ActionRequest;
import soma.edupilms.progress.service.redis.RedisService;

@Service
@RequiredArgsConstructor
public class PublishService {

    private final RedisService redisService;

    public void publish(ActionRequest actionRequest) {
        redisService.publish(actionRequest);
    }

}
