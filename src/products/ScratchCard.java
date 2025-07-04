package products;

import models.Product;

/**
 * Scratch Card product - does not expire and does not require shipping
 */
public class ScratchCard extends Product {
    
    public ScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}