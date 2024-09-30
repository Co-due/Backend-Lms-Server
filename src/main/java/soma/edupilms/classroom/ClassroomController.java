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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soma.edupilms._config.advice.AccountId;
import soma.edupilms.classroom.models.ActionInitializeRequest;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomInfoResponse;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.ClassroomUpdateRequest;
import soma.edupilms.classroom.models.MyClassroomsResponse;
import soma.edupilms.classroom.service.ClassroomService;
import soma.edupilms.web.models.SuccessResponse;

@RestController
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping("/v1/classroom")
    public ResponseEntity<SuccessResponse> createClassroom(
        @Valid @AccountId @RequestBody ClassroomCreateRequest createClassroomRequest
    ) {
        ClassroomResponse classroomResponse = classroomService.createClassroom(createClassroomRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("Success create classroom", classroomResponse));
    }

    @GetMapping("/v1/classroom")
    public ResponseEntity<SuccessResponse> getMyClassrooms(@RequestHeader("X-Account-Id") Long accountId) {
        MyClassroomsResponse myClassrooms = classroomService.getMyClassrooms(accountId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("Success retrieved my classrooms", myClassrooms));
    }

    @PostMapping("/v1/classroom/action/initialization")
    public ResponseEntity<SuccessResponse> initializeActionsInClassroom(
        @RequestBody ActionInitializeRequest actionInitializeRequest) {
        Long updateCount = classroomService.initializeActionsInClassroom(actionInitializeRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("Success update actions in classroom", updateCount));
    }

    @PatchMapping("/v1/classroom/{classroomId}")
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

    @GetMapping("/v1/classroom/info")
    public ResponseEntity<SuccessResponse> getClassroomInfo(@RequestParam Long classroomId) {
        ClassroomInfoResponse classroomInfoResponse = classroomService.getClassroomInfo(classroomId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SuccessResponse.withDetailAndResult("Success retrieved classroom info", classroomInfoResponse));
    }

}
