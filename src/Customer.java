public class Customer implements ICustomer {
    private final String name;
    private Order order; // the Subject this observer listens to

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order; // set reference; register externally (order.register(this))
    }

    @Override
    public void updateOrder() {
        if (order == null) return;
        Object msg = order.getOrderUpdate(this);
        System.out.println("[" + name + "] notification -> " + msg);
    }

    public String getName() {
        return name;
    }

    // Equality by name so Order can avoid duplicate registrations if it checks contains()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer other = (Customer) o;
        return name != null ? name.equals(other.name) : other.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "'}";
    }
}

