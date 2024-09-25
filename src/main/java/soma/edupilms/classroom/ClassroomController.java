package soma.edupilms.classroom;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soma.edupilms._config.advice.AccountId;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.ClassroomUpdateRequest;
import soma.edupilms.classroom.models.MyClassroomRoleResponse;
import soma.edupilms.classroom.service.ClassroomService;
import soma.edupilms.web.models.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createClassroom(
        @Valid @AccountId @RequestBody ClassroomCreateRequest createClassroomRequest
    ) {
        ClassroomResponse classroomResponse = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("success create classroom", classroomResponse));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> getMyClassrooms(@RequestHeader("X-Account-Id") Long accountId) {
        MyClassroomRoleResponse myClassrooms = classroomService.getMyClassrooms(accountId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("Success retrieved my classrooms", myClassrooms));
    }

    @PatchMapping("/{classroomId}")
    public ResponseEntity<SuccessResponse> updateClassroom(
        @PathVariable Long classroomId,
        @Valid @RequestBody ClassroomUpdateRequest classroomUpdateRequest
    ) {
        ClassroomResponse updatedClassroom = classroomService.updateClassroomName(
            classroomId,
            classroomUpdateRequest.getClassroomName()
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("Success update classroomName", updatedClassroom));
    }

}
