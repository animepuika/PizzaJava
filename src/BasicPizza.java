public class BasicPizza implements OrderItem {
    private final Pizza pizza;
    public BasicPizza(Pizza pizza) { this.pizza = pizza; }
    @Override public String getDescription() { return "Pizza " + pizza.toString(); }
    @Override public double getCost() { return 6.0; } // base price
}
