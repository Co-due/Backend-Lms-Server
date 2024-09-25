package soma.edupilms.classroom.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassroomUpdateRequest {

    @NotNull
    @Size(min = 2, message = "클래스룸 이름은 2글자 이상이어야 합니다.")
    private String classroomName;
}
