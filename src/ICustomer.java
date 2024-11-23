
public interface ICustomer {

	//method to update the observer, used by subject
		public void updateOrder();
		
		//attach with subject to observe
		public void setOrder(Order ord);
}
