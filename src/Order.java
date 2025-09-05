import java.util.ArrayList;
import java.util.List;

public class Order implements IOrder {
    private final List<Customer> customers = new ArrayList<>();
    private String message;
    private boolean changed;

    @Override public synchronized void register(Customer c) {
        if (c != null && !customers.contains(c)) customers.add(c);
    }
    @Override public synchronized void unregister(Customer c) { customers.remove(c); }

    @Override public synchronized void placeOrder(String details) {
        this.message = "Order placed: " + details;
        this.changed = true;
        notifyCustomers();
    }

    @Override public synchronized void deleteOrder() {
        this.message = "Order deleted";
        this.changed = true;
        notifyCustomers();
    }

    @Override public synchronized void notifyCustomers() {
        if (!changed) return;
        for (Customer c : customers) c.updateOrder();
        changed = false;
    }

    @Override public synchronized Object getOrderUpdate(Customer customer) {
        return message;
    }
}

