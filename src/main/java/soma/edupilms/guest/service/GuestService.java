package soma.edupilms.guest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.edupilms.web.client.DbServerApiClient;
import soma.edupilms.guest.models.request.RegisterGuestRequest;
import soma.edupilms.guest.models.response.ClassroomAccountResponse;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomAccountResponse registerGuest(RegisterGuestRequest registerGuestRequest) {

        return dbServerApiClient.registerGuest(registerGuestRequest);
    }

}
