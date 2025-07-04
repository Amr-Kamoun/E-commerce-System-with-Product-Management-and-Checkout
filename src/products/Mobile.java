package products;

import models.Product;

/**
 * Mobile product - does not expire and does not require shipping
 */
public class Mobile extends Product {
    
    public Mobile(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}