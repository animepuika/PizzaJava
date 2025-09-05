public class WithDrink extends OrderDecorator {
    private final Drinks drink;
    public WithDrink(OrderItem item, Drinks drink) { super(item); this.drink = drink; }
    @Override public String getDescription() { return item.getDescription() + ", +Drink(" + drink.getName() + ")"; }
    @Override public double getCost() { return item.getCost() + drink.getPrice(); }
}
