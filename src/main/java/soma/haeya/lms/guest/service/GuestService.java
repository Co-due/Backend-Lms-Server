package soma.haeya.lms.guest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soma.haeya.lms.common.client.DbServerApiClient;
import soma.haeya.lms.guest.models.request.RegisterGuestRequest;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final DbServerApiClient dbServerApiClient;

    public void registerGuest(RegisterGuestRequest registerGuestRequest) {

        dbServerApiClient.registerGuest(registerGuestRequest);
    }

}
