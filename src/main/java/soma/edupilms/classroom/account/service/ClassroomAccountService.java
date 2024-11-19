package soma.edupilms.classroom.account.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import soma.edupilms.classroom.account.exception.ClassroomAccountException;
import soma.edupilms.classroom.account.models.CheckClassroomAccountRole;
import soma.edupilms.classroom.account.models.ClassroomAccountResponse;
import soma.edupilms.classroom.account.models.GuestsResponse;
import soma.edupilms.classroom.account.models.RegisterGuestRequest;
import soma.edupilms.classroom.exception.ClassroomException;
import soma.edupilms.progress.service.models.ActionStatus;
import soma.edupilms.web.client.MetaServerApiClient;
import soma.edupilms.web.exception.ErrorEnum;
import soma.edupilms.web.exception.MetaServerException;
import soma.edupilms.web.models.ErrorResponse;

@Service
@RequiredArgsConstructor
public class ClassroomAccountService {

    private final MetaServerApiClient metaServerApiClient;

    public ClassroomAccountResponse registerClassroomAccount(RegisterGuestRequest registerGuestRequest) {
        try {
            return metaServerApiClient.registerClassroomAccount(registerGuestRequest);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-400001")) {
                throw new ClassroomAccountException(ErrorEnum.EMAIL_NOT_MATCH);
            } else if (errorResponse.getCode().equals("DB-400101")) {
                throw new ClassroomAccountException(ErrorEnum.CLASSROOM_NOT_FOUND);
            } else if (errorResponse.getCode().equals("DB-400201")) {
                throw new ClassroomAccountException(ErrorEnum.ALREADY_REGISTER);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }

        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }
    }

    public GuestsResponse getAllClassroomAccounts(Long classroomId) {
        try {
            List<ClassroomAccountResponse> guests = metaServerApiClient.getClassroomAccountBy(classroomId);
            return new GuestsResponse(guests);
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }

    }

    public GuestsResponse getClassroomAccountsWithoutDefaultAction(Long classroomId) {
        List<ClassroomAccountResponse> guests;
        try {
            guests = metaServerApiClient.getClassroomAccountBy(classroomId);
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }

        List<ClassroomAccountResponse> filteredGuests = guests.stream()
            .filter(guest -> !guest.getStatus().equals(ActionStatus.DEFAULT))
            .toList();

        return new GuestsResponse(filteredGuests);
    }

    public void deleteClassroomAccount(Long classroomAccountId) {
        try {
            metaServerApiClient.deleteClassroomAccount(classroomAccountId);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }
            if (errorResponse.getCode().equals("DB-404200")) {
                throw new ClassroomAccountException(ErrorEnum.CLASSROOM_ACCOUNT_NOT_FOUND);
            } else {
                throw new MetaServerException(ErrorEnum.NOT_MATCH_ERROR);
            }

        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }

    }

    public CheckClassroomAccountRole checkClassroomAccountRole(Long accountId, Long classroomId) {
        try {
            return metaServerApiClient.checkClassroomAccountRole(accountId, classroomId);
        } catch (ResourceAccessException e) {
            throw new ClassroomException(ErrorEnum.RESOURCE_ACCESS_EXCEPTION);
        } catch (Exception e) {
            throw new ClassroomException(ErrorEnum.TASK_FAIL);
        }
    }

    public String getClassroomAccountCode(Long classroomAccountId) {
        return metaServerApiClient.getCode(classroomAccountId);
    }
    
}
