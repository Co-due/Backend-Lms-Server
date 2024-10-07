package soma.edupilms.classroom.account.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.account.models.ClassroomAccountResponse;
import soma.edupilms.classroom.account.models.GuestsResponse;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.progress.service.models.ActionStatus;
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

    public GuestsResponse getClassroomAccountsWithoutDefaultAction(Long classroomId) {
        List<ClassroomAccountResponse> guests = dbServerApiClient.getClassroomAccountBy(classroomId);

        List<ClassroomAccountResponse> filteredGuests = guests.stream()
            .filter(guest -> !guest.getStatus().equals(ActionStatus.DEFAULT))
            .toList();

        return new GuestsResponse(filteredGuests);
    }

    public void deleteClassroomAccount(Long classroomAccountId) {
        dbServerApiClient.deleteClassroomAccount(classroomAccountId);
    }

}
