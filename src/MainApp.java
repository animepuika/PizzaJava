public class MainApp {
    public static void main(String[] args) {
        // ===== Creational: Builder =====
        Pizza custom = new Pizza.Builder()
                .base("Thin").size("Large")
                .addTopping("Pepperoni").addTopping("Mushroom")
                .extraCheese(true).build();

        // ===== Creational: Factory Method =====
        Drinks cola = DrinkFactory.create(DrinkType.COLA);
        Drinks water = DrinkFactory.create(DrinkType.WATER);

        // ===== Structural: Decorator =====
        OrderItem item = new BasicPizza(custom);
        item = new WithDrink(item, cola);
        item = new WithSide(item, new Sides("Garlic Bread", 3.0));
        System.out.println("[Decorator] " + item.getDescription() + " | Cost: " + item.getCost());

        // ===== Structural: Bridge (two implementors) =====
        User customerUser = new User(new CustomerView()); // implementor #1
        customerUser.access();
        User adminUser = new User(new AdminView());       // implementor #2
        adminUser.access();

        // ===== Behavioral: Observer =====
        Order order = new Order();
        Customer c1 = new Customer("Bob");
        Customer c2 = new Customer("Eve");
        c1.setOrder(order); c2.setOrder(order);
        order.register(c1); order.register(c2);
        order.placeOrder("Large Pepperoni + Cola");
        order.deleteOrder();

        // ===== Behavioral: Strategy =====
        Checkout checkout = new Checkout();
        checkout.total(item); // Regular

        checkout.setStrategy(new StudentDiscountPricing()); // swap strategy at runtime
        checkout.total(item);

        // Extra factory + decorator example (second instance)
        Drinks juice = DrinkFactory.create(DrinkType.JUICE);
        OrderItem item2 = new WithDrink(
                new BasicPizza(new Pizza.Builder().base("GlutenFree").size("Small").build()),
                juice
        );
        checkout.total(item2);
    }
}

