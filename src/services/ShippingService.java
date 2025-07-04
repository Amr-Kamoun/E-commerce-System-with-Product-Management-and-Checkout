package services;

import interfaces.Shippable;
import models.CartItem;
import java.util.List;

/**
 * Service for handling shipping operations
 */
public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 10.0; // $10 per kg
    private static final double BASE_SHIPPING_FEE = 5.0; // Base fee of $5
    
    /**
     * Calculate shipping fee based on total weight
     * @param shippableItems list of items that require shipping
     * @return shipping fee
     */
    public static double calculateShippingFee(List<CartItem> shippableItems) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = shippableItems.stream()
            .mapToDouble(item -> {
                Shippable shippable = (Shippable) item.getProduct();
                return shippable.getWeight() * item.getQuantity();
            })
            .sum();
            
        return BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
    }
    
    /**
     * Process shipment and print shipment notice
     * @param shippableItems list of items to ship
     */
    public static void processShipment(List<CartItem> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }
        
        System.out.println("** Shipment notice **");
        
        double totalWeight = 0.0;
        for (CartItem item : shippableItems) {
            Shippable shippable = (Shippable) item.getProduct();
            double itemWeight = shippable.getWeight() * item.getQuantity();
            totalWeight += itemWeight;
            
            System.out.printf("%dx %s %.0fg%n", 
                            item.getQuantity(), 
                            shippable.getName(), 
                            itemWeight * 1000); // Convert kg to grams for display
        }
        
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}