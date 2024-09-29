package soma.edupilms.classroom.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.classroom.account.service.models.ClassroomAccountResponse;
import soma.edupilms.web.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomAccountResponse registerClassroomAccount(RegisterGuestRequest registerGuestRequest) {

        return dbServerApiClient.registerClassroomAccount(registerGuestRequest);
    }

}
