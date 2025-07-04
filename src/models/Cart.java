package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Shopping cart implementation
 */
public class Cart {
    private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    /**
     * Add product to cart
     * @param product the product to add
     * @param quantity the quantity to add
     * @throws IllegalArgumentException if quantity exceeds available stock
     */
    public void add(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (!product.isAvailable(quantity)) {
            if (product.isExpired()) {
                throw new IllegalArgumentException("Cannot add expired product: " + product.getName());
            } else {
                throw new IllegalArgumentException(
                    String.format("Insufficient stock for %s. Available: %d, Requested: %d", 
                                product.getName(), product.getQuantity(), quantity));
            }
        }
        
        // Check if product already exists in cart
        Optional<CartItem> existingItem = items.stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst();
            
        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() + quantity;
            if (!product.isAvailable(newQuantity)) {
                throw new IllegalArgumentException(
                    String.format("Total quantity exceeds available stock for %s. Available: %d, Total requested: %d", 
                                product.getName(), product.getQuantity(), newQuantity));
            }
            existingItem.get().setQuantity(newQuantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
    }
    
    /**
     * Remove product from cart
     * @param product the product to remove
     */
    public void remove(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }
    
    /**
     * Clear all items from cart
     */
    public void clear() {
        items.clear();
    }
    
    /**
     * Check if cart is empty
     * @return true if cart is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    /**
     * Get all items in cart
     * @return list of cart items
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    /**
     * Calculate subtotal of all items in cart
     * @return subtotal amount
     */
    public double getSubtotal() {
        return items.stream()
                   .mapToDouble(CartItem::getTotalPrice)
                   .sum();
    }
    
    /**
     * Get all shippable items from cart
     * @return list of cart items that require shipping
     */
    public List<CartItem> getShippableItems() {
        return items.stream()
                   .filter(item -> item.getProduct().requiresShipping())
                   .collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cart is empty";
        }
        
        StringBuilder sb = new StringBuilder("Cart contents:\n");
        for (CartItem item : items) {
            sb.append(String.format("- %s (%.2f each)\n", 
                                  item.toString(), item.getProduct().getPrice()));
        }
        sb.append(String.format("Subtotal: $%.2f", getSubtotal()));
        return sb.toString();
    }
}