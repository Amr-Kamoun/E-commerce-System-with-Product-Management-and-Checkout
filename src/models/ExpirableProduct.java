package models;

import java.time.LocalDate;

/**
 * Abstract class for products that can expire
 */
public abstract class ExpirableProduct extends Product {
    protected LocalDate expirationDate;
    
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        this.expirationDate = expirationDate;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f (Qty: %d, Expires: %s)", 
                           name, price, quantity, expirationDate);
    }
}