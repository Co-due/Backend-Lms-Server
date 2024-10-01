package soma.edupilms.classroom.account.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestsResponse {

    private List<ClassroomAccountResponse> guests;

    public GuestsResponse(List<ClassroomAccountResponse> guests) {
        this.guests = guests;
    }
}
