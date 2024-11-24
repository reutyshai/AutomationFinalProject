package MockServer;

import lombok.Builder;
import lombok.NonNull;
import org.mockserver.model.HttpRequest;

import java.util.Map;

@Builder
public class Request {
    private String url;
    private Method method;
    private String body;
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    private Map<String, String> headers;
    private Map<String, String> queryParams;

    public String getUrl() {
        return url;
    }

    private HttpRequest httpRequest;

    public Request() {
        this.httpRequest = HttpRequest.request();
    }

    private void addQueryParams() {
        if (queryParams != null && !queryParams.isEmpty()) {
            queryParams.forEach((key, value) -> httpRequest.withQueryStringParameter(key, value));
        }
    }

    private void addBody() {
        if (body != null && !body.isEmpty()) {
            httpRequest.withBody(body);
        }
    }

    private void addHeaders() {
        if (headers != null && !headers.isEmpty()) {
            headers.forEach((key, value) -> httpRequest.withHeader(key, value));
        }
    }

    private void validateUrlAndMethod() {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        if(method==null)
            throw new IllegalArgumentException("Method cannot be null");

    }

    private void createRequest() {
        validateUrlAndMethod();
        httpRequest.withPath(url);
        httpRequest.withMethod(method.toString());

        addQueryParams();
        addBody();
        addHeaders();
    }

    public HttpRequest getHttpRequest() {
        createRequest();
        return httpRequest;
    }
}
