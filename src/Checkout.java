public class Checkout {
    private PricingStrategy strategy = new RegularPricing();
    public void setStrategy(PricingStrategy strategy) { this.strategy = strategy; }

    public double total(OrderItem item) {
        double subtotal = item.getCost();
        double total = strategy.apply(subtotal);
        System.out.println("Pricing Strategy: " + strategy.name() + " | Subtotal=" + subtotal + " -> Total=" + total);
        return total;
    }
}
