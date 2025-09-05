Repository link https://github.com/animepuika/PizzaJava

I worked alone because I am a 4 year.

Design Patterns that are being used:
# Pizza Ordering System (Java)

Console app that shows six classic design patterns in a pizza-ordering context.  
Clean construction for pizzas, composable extras, decoupled views, order notifications, and swappable pricing rules.

---

## Patterns

### Creational
- **Builder** — `Pizza.Builder` (`Pizza.java`)  
  Fluent creation of immutable pizzas; replaces telescoping constructors.
- **Factory Method** — `DrinkFactory.create(DrinkType)` (`DrinkFactory.java`, `DrinkType.java`)  
  Central place to construct `Drinks` from an enum.

### Structural
- **Bridge** — `User` ↔ `IUserType` with `CustomerView` and `AdminView`  
  `User` delegates to a view implementor; can switch implementations at runtime.
- **Decorator** — `OrderItem`, `OrderDecorator`, `BasicPizza`, `WithDrink`, `WithSide`  
  Compose extras around a base item; description and cost accumulate.

### Behavioral
- **Observer** — `Order` ↔ `Customer` via `IOrder`, `ICustomer`  
  Customers register with an order and are notified when the order changes.
- **Strategy** — `Checkout` + `PricingStrategy` (`RegularPricing`, `StudentDiscountPricing`)  
  Pricing rule is pluggable and can be swapped at runtime.

---

## Quick usage (from `MainApp.java`)

```java
Builder
Pizza custom = new Pizza.Builder()
        .base("Thin").size("Large")
        .addTopping("Pepperoni").addTopping("Mushroom")
        .extraCheese(true).build();

Factory Method
Drinks cola  = DrinkFactory.create(DrinkType.COLA);
Drinks water = DrinkFactory.create(DrinkType.WATER);

Decorator (compose extras)
OrderItem item = new BasicPizza(custom);
item = new WithDrink(item, cola);
item = new WithSide(item, new Sides("Garlic Bread", 3.0));
System.out.println("[Decorator] " + item.getDescription() + " | Cost: " + item.getCost());

Bridge (swap implementations at runtime)
User user = new User(new CustomerView());
user.access();
user.setUserType(new AdminView());
user.access();

Observer (register customers; notify on changes)
Order order = new Order();
Customer bob = new Customer("Bob");
Customer eve = new Customer("Eve");
bob.setOrder(order); eve.setOrder(order);
order.register(bob); order.register(eve);
order.placeOrder("Large Pepperoni + Cola");
order.deleteOrder();

Strategy (switch pricing)
Checkout checkout = new Checkout();
checkout.total(item); 
checkout.setStrategy(new StudentDiscountPricing());
checkout.total(item);
