public class WithSide extends OrderDecorator {
    private final Sides side;
    public WithSide(OrderItem item, Sides side) { super(item); this.side = side; }
    @Override public String getDescription() { return item.getDescription() + ", +Side(" + side.getName() + ")"; }
    @Override public double getCost() { return item.getCost() + side.getPrice(); }
}
