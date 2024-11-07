package soma.edupilms.classroom.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.View;
import soma.edupilms.classroom.exception.ClassroomException;
import soma.edupilms.classroom.models.ActionInitRequest;
import soma.edupilms.classroom.models.ClassroomCreateRequest;
import soma.edupilms.classroom.models.ClassroomInfoResponse;
import soma.edupilms.classroom.models.ClassroomResponse;
import soma.edupilms.classroom.models.MyClassroomResponse;
import soma.edupilms.classroom.models.MyClassroomsResponse;
import soma.edupilms.web.client.MetaServerApiClient;
import soma.edupilms.web.exception.ErrorEnum;
import soma.edupilms.web.exception.MetaServerException;
import soma.edupilms.web.models.ErrorResponse;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final MetaServerApiClient metaServerApiClient;
    private final View error;

    public ClassroomResponse createClassroom(ClassroomCreateRequest createClassroomRequest) {
        try {
            return metaServerApiClient.createClassroom(createClassroomRequest);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-409101")) {
                throw new ClassroomException(ErrorEnum.CLASSROOM_NAME_DUPLICATE);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }

        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }
    }

    public MyClassroomsResponse getMyClassrooms(Long accountId) {
        try {
            List<MyClassroomResponse> myClassrooms = metaServerApiClient.getMyClassrooms(accountId);
            return new MyClassroomsResponse(myClassrooms);

        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-400001")) {
                throw new ClassroomException(ErrorEnum.CLASSROOM_NOT_FOUND);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }

    }

    public void deleteClassroom(Long classroomId) {
        try {
            metaServerApiClient.deleteClassroom(classroomId);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-400101")) {
                throw new ClassroomException(ErrorEnum.CLASSROOM_NOT_FOUND);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }


    }

    public Long initAllActionsInClassroom(ActionInitRequest actionInitRequest) {
        try {
            return metaServerApiClient.initAllActionStatus(actionInitRequest);

        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-400101")) {
                throw new ClassroomException(ErrorEnum.CLASSROOM_NOT_FOUND);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }
    }

    public ClassroomResponse updateClassroomName(Long classroomId, String classroomName) {
        try {
            return metaServerApiClient.changeClassroomName(classroomId, classroomName);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-400101")) {
                throw new ClassroomException(ErrorEnum.CLASSROOM_NOT_FOUND);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }
    }

    public ClassroomInfoResponse getClassroomInfo(Long classroomId) {
        try {
            return metaServerApiClient.getClassroomInfo(classroomId);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-400101")) {
                throw new ClassroomException(ErrorEnum.CLASSROOM_NOT_FOUND);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }
    }
}
