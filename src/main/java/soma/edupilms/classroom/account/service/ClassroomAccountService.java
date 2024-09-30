package soma.edupilms.classroom.account.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.account.models.ClassroomAccountResponse;
import soma.edupilms.classroom.account.models.GuestsResponse;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.web.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomAccountResponse registerClassroomAccount(RegisterGuestRequest registerGuestRequest) {
        return dbServerApiClient.registerClassroomAccount(registerGuestRequest);
    }

    public GuestsResponse getAllClassroomAccounts(Long classroomId) {
        List<ClassroomAccountResponse> guests = dbServerApiClient.getClassroomAccountBy(classroomId);
        return new GuestsResponse(guests);
    }

}
