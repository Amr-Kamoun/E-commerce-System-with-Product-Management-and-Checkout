package models;

import java.time.LocalDate;

/**
 * Abstract base class for all products in the e-commerce system
 */
public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    
    public Product(String name, double price, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    
    // Setters
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    
    /**
     * Check if product is available for purchase
     * @param requestedQuantity the quantity customer wants to buy
     * @return true if available, false otherwise
     */
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity && !isExpired();
    }
    
    /**
     * Reduce product quantity after purchase
     * @param soldQuantity quantity sold
     */
    public void reduceQuantity(int soldQuantity) {
        if (soldQuantity > quantity) {
            throw new IllegalStateException("Cannot sell more than available quantity");
        }
        this.quantity -= soldQuantity;
    }
    
    /**
     * Check if product is expired (default implementation for non-expirable products)
     * @return false by default, overridden in expirable products
     */
    public boolean isExpired() {
        return false;
    }
    
    /**
     * Check if product requires shipping (default implementation)
     * @return false by default, overridden in shippable products
     */
    public boolean requiresShipping() {
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f (Qty: %d)", name, price, quantity);
    }
}