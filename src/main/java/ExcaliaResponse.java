import java.util.List;
import java.util.Map;

public record ExcaliaResponse(
        Summary summary,
        List<Shop> shops,
        Map<String, List<PriceHistory>> histories
){}
