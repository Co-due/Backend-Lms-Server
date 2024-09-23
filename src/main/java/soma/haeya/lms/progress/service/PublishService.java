package soma.haeya.lms.progress.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.haeya.lms.progress.models.ActionRequest;
import soma.haeya.lms.progress.service.redis.RedisService;

@Service
@RequiredArgsConstructor
public class PublishService {

    private final RedisService redisService;

    public void publish(ActionRequest actionRequest) {
        redisService.publish(actionRequest);
    }

}
