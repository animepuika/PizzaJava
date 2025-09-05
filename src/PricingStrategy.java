public interface PricingStrategy {
    double apply(double subtotal);
    String name();
}
