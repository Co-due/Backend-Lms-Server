package soma.haeya.lms.common.config.argumentresolver;

import java.io.InputStream;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

public class CustomHttpInputMessage implements HttpInputMessage {

    private final HttpHeaders headers;
    private final InputStream body;

    public CustomHttpInputMessage(HttpHeaders headers, InputStream body) {
        this.headers = headers;
        this.body = body;
    }

    @Override
    @NonNull
    public HttpHeaders getHeaders() {
        return headers;
    }

    @Override
    @NonNull
    public InputStream getBody() {
        return body;
    }
}