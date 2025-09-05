public interface IOrder {
    void register(Customer c);
    void unregister(Customer c);
    void placeOrder(String details);
    void deleteOrder();
    void notifyCustomers();
    Object getOrderUpdate(Customer c);
}

