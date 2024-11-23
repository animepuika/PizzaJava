
public interface IOrder {
	public void placeOrder(Customer cus);
	public void deleteOrder(Customer cus);
	
	//method to notify observers of change
	public void notifyCustomers();
	
	//method to get updates from subject
	public Object getOrderUpdate(Customer cus);
}
