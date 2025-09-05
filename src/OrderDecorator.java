public abstract class OrderDecorator implements OrderItem {
    protected final OrderItem item;
    public OrderDecorator(OrderItem item) { this.item = item; }
}
