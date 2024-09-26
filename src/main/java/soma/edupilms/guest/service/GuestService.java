package soma.edupilms.guest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.guest.models.ClassroomAccountResponse;
import soma.edupilms.guest.models.RegisterGuestRequest;
import soma.edupilms.web.client.DbServerApiClient;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomAccountResponse registerGuest(RegisterGuestRequest registerGuestRequest) {

        return dbServerApiClient.registerGuest(registerGuestRequest);
    }

}
