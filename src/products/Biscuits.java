package products;

import models.ExpirableShippableProduct;
import java.time.LocalDate;

/**
 * Biscuits product - expires and requires shipping
 */
public class Biscuits extends ExpirableShippableProduct {
    
    public Biscuits(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate, weight);
    }
    
    // Convenience constructor with default weight per unit
    public Biscuits(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity, expirationDate, 0.7); // 700g per unit
    }
}