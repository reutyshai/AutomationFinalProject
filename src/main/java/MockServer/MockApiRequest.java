package MockServer;

import DataBaseConnected.DatabaseUtils;
import DataBaseConnected.JsonConvert;
import Readers.CsvFileReader;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.ArrayList;

public class MockApiRequest {


    private final ClientAndServer mockServer;

    public MockApiRequest(int port) {
        mockServer = ClientAndServer.startClientAndServer(port);
    }

    public void stopMockServer() {
        mockServer.stop();
    }

    public void configureGetMethod(String url) {
       HttpRequest httpRequest= HttpRequest.request();

        httpRequest.withPath(url);
        httpRequest.withMethod("GET");
        HttpResponse httpResponse=HttpResponse.response();
        String tableName= CsvFileReader.findTableByUrl(url,"");//לראות איך לשלוף את הpath של הcsv

        mockServer
                .when(httpRequest)
                .respond(
                        httpResponse.withBody(
                                JsonConvert.convertToJSON(DatabaseUtils.fetchDataFromDatabase(new ArrayList<>(),tableName))
                ).withStatusCode(200));
    }

    public void configureGetByIdMethod(String url,int id) {
        HttpRequest httpRequest= HttpRequest.request();

        httpRequest.withPath(url);
        httpRequest.withMethod("GET");
        httpRequest.withQueryStringParameter("id",Integer.toString(id));
        String tableName= CsvFileReader.findTableByUrl(url,"");//לראות איך לשלוף את הpath של הcsv
        HttpResponse httpResponse=HttpResponse.response();
        mockServer
                .when(httpRequest)
                .respond(
                        httpResponse.withBody(
                                JsonConvert.convertToJSON(DatabaseUtils.fetchDataFromDatabaseById(new ArrayList<>(),tableName,id))
                        ).withStatusCode(200));
    }
}


