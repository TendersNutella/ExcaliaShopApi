package exemples;

import beans.Item;
import mockdata.MockData;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ExcaliaAPI {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        List<Item> items = MockData.getItemsFromShops("shop_fermier");
        items.forEach(System.out::println);

    }
}