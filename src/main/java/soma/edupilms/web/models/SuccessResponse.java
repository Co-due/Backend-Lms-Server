package soma.edupilms.web.models;

import java.util.Collections;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SuccessResponse {

    private static final String DEFAULT_CODE = "CM-200000";

    private String code;
    private String detail;
    private Object result = Collections.EMPTY_MAP;

    @Builder
    public SuccessResponse(String code, String detail, Object result) {
        this.code = (code != null) ? code : DEFAULT_CODE;
        this.detail = detail;
        this.result = result;
    }

    public static SuccessResponse withDetailAndResult(String detail, Object result) {
        return SuccessResponse.builder()
            .detail(detail)
            .result(result)
            .build();
    }

    public static SuccessResponse withResult(Object result) {
        return SuccessResponse.builder()
            .result(result)
            .build();
    }

}
