# Pizza Ordering System (Java)

A console application that demonstrates six classic **Gang of Four (GoF) design patterns** in a pizza-ordering context.  
The system highlights **clean construction for pizzas, composable extras, decoupled views, order notifications, and swappable pricing rules**.

---

## Project Info

- **Repository link:** [PizzaJava on GitHub](https://github.com/animepuika/PizzaJava)  
- **Technologies:** Java, OOP, Design Patterns

---

## Author

**Kristers Dāvis Gruziņš** – Developer and fourth-year student.

---

## Development

This project was independently designed and implemented as a demonstration of multiple design patterns in a cohesive domain.

1. **Planning Phase**: Analyzed the requirements and selected which design patterns to implement for each category (Creational, Structural, Behavioral).

2. **Design Decisions**:  
   - Selected the pizza ordering domain as a suitable context for demonstrating multiple design patterns.  
   - Designed the class structure and interfaces to ensure the patterns integrate cleanly.

3. **Implementation**:  
   - Implemented all design patterns, core code structure, `MainApp` logic, and README documentation.

4. **Code Review and Testing**:  
   - Iteratively reviewed and tested the code to ensure correctness and proper pattern implementation.

5. **Documentation**:  
   - Created this README to explain the intent, structure, and interaction of the implemented patterns.

The repository hosts all code in the `src/` directory, and this README documents the implemented design patterns and their interactions.

---

## Implemented Design Patterns

This project showcases six design patterns across the **Creational**, **Structural**, and **Behavioral** categories.  
Each pattern section explains **what it does**, **why it fits**, and **how it interacts** with other patterns.

---

### Creational

#### Builder — `Pizza.Builder` (`Pizza.java`)
- **What it does:** Provides a fluent interface for creating immutable `Pizza` objects, avoiding long telescoping constructors.  
- **Why here:** A pizza can have many optional attributes (base, size, toppings, extras). Builder keeps creation clean and flexible.  
- **How it interacts:** Works with **Decorator** — pizzas built this way can be wrapped with drinks/sides.

```java
Pizza custom = new Pizza.Builder()
    .base("Thin")
    .size("Large")
    .addTopping("Pepperoni")
    .addTopping("Mushroom")
    .extraCheese(true)
    .build();
```

---

#### Factory Method — `DrinkFactory.create(DrinkType)` (`DrinkFactory.java`, `DrinkType.java`)
- **What it does:** Centralizes creation of `Drinks` objects using an enum for type safety.  
- **Why here:** Makes drink instantiation consistent and easy to extend.  
- **How it interacts:** Works with **Decorator** — drinks from the factory can be attached to pizzas as extras.

```java
Drinks cola  = DrinkFactory.create(DrinkType.COLA);
Drinks water = DrinkFactory.create(DrinkType.WATER);
```

---

### Structural

#### Bridge — `User` ↔ `IUserType` with `CustomerView` and `AdminView`
- **What it does:** Separates the `User` abstraction from its implementation (`CustomerView` vs `AdminView`).  
- **Why here:** Users can take different roles while sharing the same base structure.  
- **How it interacts:** Integrates with **Observer** — customers receive updates, admins can manage orders.

```java
User user = new User(new CustomerView());
user.accessUser();
user.setUserType(new AdminView());
user.accessUser();
```

---

#### Decorator — `OrderItem`, `OrderDecorator`, `BasicPizza`, `WithDrink`, `WithSide`
- **What it does:** Dynamically adds responsibilities (drinks, sides) around a base pizza. Cost and description accumulate as layers are added.  
- **Why here:** Orders are naturally composable — avoids dozens of subclasses.  
- **How it interacts:**  
  - Wraps **Builder**-made pizzas.  
  - Adds **Factory Method** drinks.  
  - Works with **Strategy** to apply pricing rules.

```java
OrderItem item = new BasicPizza(custom);
item = new WithDrink(item, cola);
item = new WithSide(item, new Sides("Garlic Bread", 3.0));
System.out.println("[Decorator] " + item.getDescription() + " | Cost: " + item.getCost());
```

---

### Behavioral

#### Observer — `Order` ↔ `Customer` via `IOrder`, `ICustomer`
- **What it does:** Customers subscribe to an order and are notified when it changes.  
- **Why here:** Mirrors real-world expectations of order tracking.  
- **How it interacts:** Works with **Bridge** so different user types receive updates.

```java
Order order = new Order();
Customer bob = new Customer("Bob");
Customer eve = new Customer("Eve");

bob.setOrder(order); 
eve.setOrder(order);

order.register(bob); 
order.register(eve);

order.placeOrder("Large Pepperoni + Cola");
order.deleteOrder();
```

---

#### Strategy — `Checkout` + `PricingStrategy` (`RegularPricing`, `StudentDiscountPricing`)
- **What it does:** Encapsulates different pricing rules that can be swapped at runtime.  
- **Why here:** Restaurants often apply discounts (e.g., student deals). New strategies can be added without changing core logic.  
- **How it interacts:** Combines with **Decorator** to calculate final prices for flexible order compositions.

```java
Checkout checkout = new Checkout();
checkout.total(item); 

checkout.setStrategy(new StudentDiscountPricing());
checkout.total(item);
```

---

## How the Patterns Work Together

- **Builder + Decorator:** Build pizzas and extend them with extras.  
- **Factory Method + Decorator:** Create drinks centrally and attach them to orders.  
- **Bridge + Observer:** Customers and admins interact differently but still receive updates.  
- **Decorator + Strategy:** Flexible order composition with adaptable pricing rules.

This synergy makes the system **modular, extensible, and realistic** for a pizza-ordering scenario.