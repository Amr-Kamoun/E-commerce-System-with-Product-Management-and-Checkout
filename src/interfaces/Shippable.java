package interfaces;

/**
 * Interface for items that can be shipped
 * Contains methods required by the ShippingService
 */
public interface Shippable {
    String getName();
    double getWeight();
}