package models;

/**
 * Customer class representing a customer in the e-commerce system
 */
public class Customer {
    private String name;
    private double balance;
    
    public Customer(String name, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Customer balance cannot be negative");
        }
        
        this.name = name;
        this.balance = balance;
    }
    
    public String getName() { return name; }
    public double getBalance() { return balance; }
    
    /**
     * Deduct amount from customer balance
     * @param amount amount to deduct
     * @throws IllegalStateException if insufficient balance
     */
    public void deductBalance(double amount) {
        if (amount > balance) {
            throw new IllegalStateException(
                String.format("Insufficient balance. Available: $%.2f, Required: $%.2f", 
                            balance, amount));
        }
        this.balance -= amount;
    }
    
    /**
     * Add amount to customer balance
     * @param amount amount to add
     */
    public void addBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        this.balance += amount;
    }
    
    @Override
    public String toString() {
        return String.format("Customer: %s (Balance: $%.2f)", name, balance);
    }
}