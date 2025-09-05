public class RegularPricing implements PricingStrategy {
    @Override public double apply(double subtotal) { return subtotal; }
    @Override public String name() { return "Regular"; }
}
