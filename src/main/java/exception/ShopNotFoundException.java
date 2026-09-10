package exception;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(String shopId) {
        super("No shop with this id {" + shopId + "} was found.");
    }
}
