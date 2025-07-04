import models.*;
import products.*;
import services.CheckoutService;
import java.time.LocalDate;

/**
 * Demo class to showcase the e-commerce system functionality
 */
public class ECommerceDemo {
    
    public static void main(String[] args) {
        System.out.println("=== E-Commerce System Demo ===\n");
        
        // Create products
        Product cheese = new Cheese("Cheese", 100.0, 10, LocalDate.now().plusDays(7));
        Product tv = new TV("TV", 500.0, 5);
        Product scratchCard = new ScratchCard("Mobile Scratch Card", 25.0, 20);
        Product biscuits = new Biscuits("Biscuits", 150.0, 8, LocalDate.now().plusDays(14));
        Product mobile = new Mobile("Mobile Phone", 800.0, 3);
        
        // Create customer
        Customer customer = new Customer("John Doe", 2000.0);
        
        System.out.println("Initial customer: " + customer);
        System.out.println();
        
        // Test Case 1: Successful checkout with mixed products
        System.out.println("=== Test Case 1: Successful Checkout ===");
        testSuccessfulCheckout(customer, cheese, tv, scratchCard, biscuits);
        
        System.out.println("\nCustomer after first purchase: " + customer);
        System.out.println();
        
        // Test Case 2: Empty cart error
        System.out.println("=== Test Case 2: Empty Cart Error ===");
        testEmptyCartError(customer);
        
        // Test Case 3: Insufficient balance error
        System.out.println("=== Test Case 3: Insufficient Balance Error ===");
        testInsufficientBalanceError(customer, mobile);
        
        // Test Case 4: Out of stock error
        System.out.println("=== Test Case 4: Out of Stock Error ===");
        testOutOfStockError(customer, tv);
        
        // Test Case 5: Expired product error
        System.out.println("=== Test Case 5: Expired Product Error ===");
        testExpiredProductError(customer);
        
        // Test Case 6: Non-shippable items only
        System.out.println("=== Test Case 6: Non-Shippable Items Only ===");
        testNonShippableItemsOnly(customer, scratchCard);
        
        System.out.println("\n=== Demo Complete ===");
    }
    
    private static void testSuccessfulCheckout(Customer customer, Product cheese, 
                                             Product tv, Product scratchCard, Product biscuits) {
        Cart cart = new Cart();
        
        try {
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
            cart.add(biscuits, 1);
            
            System.out.println("Cart before checkout:");
            System.out.println(cart);
            System.out.println();
            
            CheckoutService.checkout(customer, cart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void testEmptyCartError(Customer customer) {
        Cart emptyCart = new Cart();
        
        try {
            CheckoutService.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testInsufficientBalanceError(Customer customer, Product mobile) {
        Cart cart = new Cart();
        
        try {
            cart.add(mobile, 3); // 3 * $800 = $2400, but customer has less
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testOutOfStockError(Customer customer, Product tv) {
        Cart cart = new Cart();
        
        try {
            // TV quantity was reduced in first purchase, trying to buy more than available
            cart.add(tv, 10);
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testExpiredProductError(Customer customer) {
        Cart cart = new Cart();
        
        try {
            // Create an expired product
            Product expiredCheese = new Cheese("Expired Cheese", 50.0, 5, LocalDate.now().minusDays(1));
            cart.add(expiredCheese, 1);
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testNonShippableItemsOnly(Customer customer, Product scratchCard) {
        Cart cart = new Cart();
        
        try {
            cart.add(scratchCard, 2);
            
            System.out.println("Cart with non-shippable items only:");
            System.out.println(cart);
            System.out.println();
            
            CheckoutService.checkout(customer, cart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
}