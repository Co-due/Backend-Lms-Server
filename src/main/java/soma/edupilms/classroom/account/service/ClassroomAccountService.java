package soma.edupilms.classroom.account.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.classroom.account.models.CheckClassroomAccountRole;
import soma.edupilms.classroom.account.models.ClassroomAccountResponse;
import soma.edupilms.classroom.account.models.GuestsResponse;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.progress.service.models.ActionStatus;
import soma.edupilms.web.client.MetaServerApiClient;

@Service
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final MetaServerApiClient metaServerApiClient;

    public ClassroomAccountResponse registerClassroomAccount(RegisterGuestRequest registerGuestRequest) {
        return metaServerApiClient.registerClassroomAccount(registerGuestRequest);
    }

    public GuestsResponse getAllClassroomAccounts(Long classroomId) {
        List<ClassroomAccountResponse> guests = metaServerApiClient.getClassroomAccountBy(classroomId);
        return new GuestsResponse(guests);
    }

    public GuestsResponse getClassroomAccountsWithoutDefaultAction(Long classroomId) {
        List<ClassroomAccountResponse> guests = metaServerApiClient.getClassroomAccountBy(classroomId);

        List<ClassroomAccountResponse> filteredGuests = guests.stream()
                .filter(guest -> !guest.getStatus().equals(ActionStatus.DEFAULT))
                .toList();

        return new GuestsResponse(filteredGuests);
    }

    public void deleteClassroomAccount(Long classroomAccountId) {
        metaServerApiClient.deleteClassroomAccount(classroomAccountId);
    }

    public CheckClassroomAccountRole checkClassroomAccountRole(Long accountId, Long classroomId) {
        return metaServerApiClient.checkClassroomAccountRole(accountId, classroomId);
    }
}
