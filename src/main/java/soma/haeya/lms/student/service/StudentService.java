package soma.haeya.lms.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import soma.haeya.lms.common.client.DbServerApiClient;
import soma.haeya.lms.common.exception.UserServerException;
import soma.haeya.lms.student.model.request.RegisterStudentRequest;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final DbServerApiClient dbServerApiClient;

    public void registerStudent(RegisterStudentRequest registerStudentRequest) {
        if (registerStudentRequest.getClassroomId() == null) {
            throw new UserServerException(HttpStatus.BAD_REQUEST, "클래스룸을 선택하세요");
        }

        dbServerApiClient.registerStudent(registerStudentRequest);
    }

}