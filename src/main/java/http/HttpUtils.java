package http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static HttpResponse<String> getResponse(URI uri) throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
