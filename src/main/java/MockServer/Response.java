package MockServer;

import lombok.Builder;
import org.mockserver.model.HttpResponse;

import java.util.Map;
@Builder
public class Response {

    private int status;
    private String body;
    private Map<String, String> headers;

    private HttpResponse httpResponse;


    private void addBody() {
        if (body != null && !body.isEmpty()) {
            httpResponse.withBody(body);
        }
    }

    private void addHeaders() {
        if (headers != null && !headers.isEmpty()) {
            headers.forEach((key, value) -> httpResponse.withHeader(key, value));
        }
    }

    public void createResponse() {
        httpResponse=HttpResponse.response().withStatusCode(status);
        addBody();
        addHeaders();
    }

    public HttpResponse getHttpResponse() {
        createResponse();
        return httpResponse;
    }
}
