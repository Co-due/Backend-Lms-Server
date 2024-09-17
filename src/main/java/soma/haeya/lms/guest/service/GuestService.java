package soma.haeya.lms.guest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.haeya.lms.common.client.DbServerApiClient;
import soma.haeya.lms.guest.models.request.RegisterGuestRequest;
import soma.haeya.lms.guest.models.response.ClassroomAccountResponse;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final DbServerApiClient dbServerApiClient;

    public ClassroomAccountResponse registerGuest(RegisterGuestRequest registerGuestRequest) {

        return dbServerApiClient.registerGuest(registerGuestRequest);
    }

}
