package products;

import models.ExpirableShippableProduct;
import java.time.LocalDate;

/**
 * Cheese product - expires and requires shipping
 */
public class Cheese extends ExpirableShippableProduct {
    
    public Cheese(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate, weight);
    }
    
    // Convenience constructor with default weight per unit
    public Cheese(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity, expirationDate, 0.2); // 200g per unit
    }
}