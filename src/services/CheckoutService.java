package services;

import models.*;
import java.util.List;

/**
 * Service for handling checkout operations
 */
public class CheckoutService {
    
    /**
     * Process checkout for customer with items in cart
     * @param customer the customer making the purchase
     * @param cart the shopping cart
     * @throws IllegalStateException for various checkout errors
     */
    public static void checkout(Customer customer, Cart cart) {
        // Validate checkout preconditions
        validateCheckout(customer, cart);
        
        // Get cart details
        List<CartItem> allItems = cart.getItems();
        List<CartItem> shippableItems = cart.getShippableItems();
        
        // Calculate amounts
        double subtotal = cart.getSubtotal();
        double shippingFee = ShippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;
        
        // Process payment
        customer.deductBalance(totalAmount);
        
        // Update product quantities
        for (CartItem item : allItems) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        
        // Process shipment if needed
        if (!shippableItems.isEmpty()) {
            ShippingService.processShipment(shippableItems);
        }
        
        // Print checkout receipt
        printCheckoutReceipt(allItems, subtotal, shippingFee, totalAmount, customer.getBalance());
        
        // Clear cart after successful checkout
        cart.clear();
    }
    
    /**
     * Validate checkout preconditions
     * @param customer the customer
     * @param cart the cart
     * @throws IllegalStateException if validation fails
     */
    private static void validateCheckout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        
        // Check for expired or out of stock products
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            
            if (product.isExpired()) {
                throw new IllegalStateException(
                    String.format("Product '%s' has expired", product.getName()));
            }
            
            if (!product.isAvailable(item.getQuantity())) {
                throw new IllegalStateException(
                    String.format("Product '%s' is out of stock. Available: %d, Required: %d", 
                                product.getName(), product.getQuantity(), item.getQuantity()));
            }
        }
        
        // Check customer balance
        double subtotal = cart.getSubtotal();
        double shippingFee = ShippingService.calculateShippingFee(cart.getShippableItems());
        double totalAmount = subtotal + shippingFee;
        
        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException(
                String.format("Insufficient balance. Required: $%.2f, Available: $%.2f", 
                            totalAmount, customer.getBalance()));
        }
    }
    
    /**
     * Print checkout receipt to console
     */
    private static void printCheckoutReceipt(List<CartItem> items, double subtotal, 
                                           double shippingFee, double totalAmount, 
                                           double remainingBalance) {
        System.out.println("** Checkout receipt **");
        
        // Print items
        for (CartItem item : items) {
            System.out.printf("%dx %s %.0f%n", 
                            item.getQuantity(), 
                            item.getProduct().getName(), 
                            item.getTotalPrice());
        }
        
        // Print totals
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        
        if (shippingFee > 0) {
            System.out.printf("Shipping %.0f%n", shippingFee);
        }
        
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.printf("Customer balance after payment: $%.2f%n", remainingBalance);
    }
}