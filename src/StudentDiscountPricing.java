public class StudentDiscountPricing implements PricingStrategy {
    @Override public double apply(double subtotal) { return subtotal * 0.9; } // 10% off
    @Override public String name() { return "Student 10% Off"; }
}
