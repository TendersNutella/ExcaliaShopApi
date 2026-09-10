package mockdata;

import exception.ShopNotFoundException;
import http.HttpUtils;
import beans.ExcaliaResponse;
import beans.Item;
import beans.Shop;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

public class MockData {
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static List<Item> getItemsFromShops(String shop) throws IOException, URISyntaxException, InterruptedException {

        URI uri = new URI("https://excalia.fr/api/economie");

        HttpResponse<String> getApiResponse = HttpUtils.getResponse(uri);

        ExcaliaResponse excaliaResponse = mapper.readValue(getApiResponse.body(), ExcaliaResponse.class);

        return excaliaResponse.shops().stream()
                .filter(s -> s.id().equalsIgnoreCase(shop))
                .findFirst()
                .map(Shop::items)
                .orElseThrow(() -> new ShopNotFoundException(shop));
    }
}
