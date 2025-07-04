# E-Commerce System

A comprehensive e-commerce system implementation in Java that handles product management, shopping cart functionality, and checkout processing with shipping capabilities.

## Features

### Product Management
- **Product Types**: Support for different product categories
  - **Expirable Products**: Cheese, Biscuits (with expiration dates)
  - **Non-Expirable Products**: TV, Mobile, Scratch Cards
  - **Shippable Products**: Items that require shipping (Cheese, TV, Biscuits)
  - **Non-Shippable Products**: Digital/virtual items (Mobile Scratch Cards)

### Shopping Cart
- Add products with quantity validation
- Automatic stock checking
- Expiration date validation
- Quantity management and updates

### Checkout System
- Comprehensive validation (empty cart, insufficient balance, stock availability)
- Automatic shipping fee calculation
- Payment processing with balance deduction
- Receipt generation with detailed breakdown

### Shipping Service
- Weight-based shipping fee calculation
- Shipment notice generation
- Support for mixed shippable/non-shippable orders

## Architecture

The system follows object-oriented design principles with:

- **Abstract Base Classes**: `Product`, `ExpirableProduct`, `ShippableProduct`
- **Interfaces**: `Shippable` for shipping service integration
- **Concrete Products**: `Cheese`, `TV`, `Mobile`, `ScratchCard`, `Biscuits`
- **Service Classes**: `CheckoutService`, `ShippingService`
- **Model Classes**: `Cart`, `CartItem`, `Customer`

## Usage Example

```java
// Create products
Product cheese = new Cheese("Cheese", 100.0, 10, LocalDate.now().plusDays(7));
Product tv = new TV("TV", 500.0, 5);
Product scratchCard = new ScratchCard("Mobile Scratch Card", 25.0, 20);

// Create customer
Customer customer = new Customer("John Doe", 2000.0);

// Create cart and add items
Cart cart = new Cart();
cart.add(cheese, 2);
cart.add(tv, 1);
cart.add(scratchCard, 1);

// Checkout
CheckoutService.checkout(customer, cart);
```

## Sample Output

```
** Shipment notice **~
2x Cheese 400g
1x TV 15000g
Total package weight 15.4kg

** Checkout receipt **
2x Cheese 200
1x TV 500
1x Mobile Scratch Card 25
----------------------
Subtotal 725
Shipping 159
Amount 884
Customer balance after payment: $1116.00
```

## Error Handling

The system handles various error scenarios:
- Empty cart validation
- Insufficient customer balance
- Out of stock products
- Expired products
- Invalid quantities

## Running the Demo

### On **Windows (PowerShell)**

In your project folder, run these commands in PowerShell:

```powershell
# Compile all Java files into the bin/ directory
javac -d bin -cp src (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })

# Change to the bin/ directory
cd bin

# Run the demo
java ECommerceDemo

```markdown
### On **Linux/Mac (bash)**

If you are on Linux or Mac, you can still use the provided `compile.sh` script:

```bash
chmod +x compile.sh
./compile.sh

Or manually:

bash
Copy code
mkdir -p bin
find src -name "*.java" > sources.txt
javac -d bin -cp src @sources.txt
cd bin
java ECommerceDemo


## Design Patterns Used

- **Template Method**: Abstract product classes define common behavior
- **Strategy Pattern**: Different shipping and pricing strategies
- **Factory Pattern**: Product creation with different characteristics
- **Observer Pattern**: Cart updates and inventory management

## Assumptions

1. **Shipping Rates**: $5 base fee + $10 per kg
2. **Weight Units**: All weights in kilograms
3. **Currency**: All prices in USD
4. **Expiration**: Products expire at end of expiration date
5. **Stock Management**: Quantities are reduced only after successful checkout
6. **Balance**: Customer balance cannot go negative

## Extension Points

The system is designed for easy extension:
- Add new product types by extending base classes
- Implement different shipping strategies
- Add discount/promotion systems
- Integrate with external payment gateways
- Add inventory management features