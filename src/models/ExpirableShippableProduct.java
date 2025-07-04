package models;

import interfaces.Shippable;
import java.time.LocalDate;

/**
 * Abstract class for products that both expire and require shipping
 */
public abstract class ExpirableShippableProduct extends ExpirableProduct implements Shippable {
    protected double weight; // in kg
    
    public ExpirableShippableProduct(String name, double price, int quantity, 
                                   LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }
    
    @Override
    public double getWeight() {
        return weight;
    }
    
    @Override
    public boolean requiresShipping() {
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f (Qty: %d, Weight: %.1fkg, Expires: %s)", 
                           name, price, quantity, weight, expirationDate);
    }
}