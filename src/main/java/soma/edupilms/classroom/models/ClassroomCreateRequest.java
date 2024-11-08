package soma.edupilms.classroom.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomCreateRequest {

    @NotNull
    private Long accountId;
    @NotNull
    @Size(min = 2, message = "The classroom name must be at least 2 characters long.")
    private String name;

}
