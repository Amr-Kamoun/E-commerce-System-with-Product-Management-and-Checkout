package models;

import interfaces.Shippable;

/**
 * Abstract class for products that require shipping
 */
public abstract class ShippableProduct extends Product implements Shippable {
    protected double weight; // in kg
    
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
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
        return String.format("%s - $%.2f (Qty: %d, Weight: %.1fkg)", 
                           name, price, quantity, weight);
    }
}