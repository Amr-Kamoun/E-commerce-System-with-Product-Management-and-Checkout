package products;

import models.ShippableProduct;

/**
 * TV product - does not expire but requires shipping
 */
public class TV extends ShippableProduct {
    
    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }
    
    // Convenience constructor with default weight
    public TV(String name, double price, int quantity) {
        super(name, price, quantity, 15.0); // 15kg default weight
    }
}