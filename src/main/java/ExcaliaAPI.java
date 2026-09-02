import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class ExcaliaAPI {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        var mapper = new ObjectMapper();
        // Ignore field
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ExcaliaResponse excaliaResponse;

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://excalia.fr/api/economie"))
                .build();

        HttpResponse<String> getApiResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        excaliaResponse = mapper.readValue(getApiResponse.body(), ExcaliaResponse.class);
        printShopItems(excaliaResponse, "shop_fermier");
    }

    public static void printShopItems(ExcaliaResponse excaliaResponse, String shop) {
        List<Item> items = excaliaResponse.shops().stream()
                .filter(shop1 -> shop1.id().equalsIgnoreCase(shop))
                .findFirst()
                .map(Shop::items)
                .orElse(Collections.emptyList());

        System.out.println(items);
    }

}