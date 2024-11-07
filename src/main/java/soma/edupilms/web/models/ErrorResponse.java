package soma.edupilms.web.models;

import java.util.Collections;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String detail;
    private Object result = Collections.EMPTY_MAP;

    public ErrorResponse(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public ErrorResponse(String code, String detail, Object result) {
        this.code = code;
        this.detail = detail;
        this.result = result;
    }
}
