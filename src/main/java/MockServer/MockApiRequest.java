package MockServer;

import org.mockserver.integration.ClientAndServer;

public class MockApiRequest {


    private final ClientAndServer mockServer;

    public MockApiRequest(int port) {
        mockServer = ClientAndServer.startClientAndServer(port);
    }


    public void stopMockServer() {
        mockServer.stop();
    }

    public void configureGetMethod(Request request, Response response) {

        mockServer
                .when(request.getHttpRequest())
                .respond(
                        response.getHttpResponse()
                );
    }
}


