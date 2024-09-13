package soma.haeya.lms.follower.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import soma.haeya.lms.common.client.DbServerApiClient;
import soma.haeya.lms.common.exception.UserServerException;
import soma.haeya.lms.follower.model.request.RegisterFollowerRequest;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final DbServerApiClient dbServerApiClient;

    public void registerFollower(RegisterFollowerRequest registerFollowerRequest) {
        if (registerFollowerRequest.getClassroomId() == null) {
            throw new UserServerException(HttpStatus.BAD_REQUEST, "클래스룸을 선택하세요");
        }

        dbServerApiClient.registerFollower(registerFollowerRequest);
    }

}
