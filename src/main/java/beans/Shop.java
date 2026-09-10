package beans;

import java.util.List;

public record Shop(
        String id,
        List<Item> items
){}
