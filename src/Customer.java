
public class  Customer implements ICustomer {

	public String name;
	public String location;
	private Order ord;
	
	public Customer(String name, String location) {
		this.name = name;
		this.location = location;
		
	}
	
	public Customer() {
		
	}

	@Override
	public void updateOrder() {
		// TODO Auto-generated method stub
		String msg = (String) ord.getOrderUpdate(this);
		if(msg == null){
			System.out.println(name+":: No new update on order");
		}else
		System.out.println(name+":: Consuming message::"+msg);
	}

	@Override
	public void setOrder(Order ord) {
		// TODO Auto-generated method stub
		this.ord = ord;
		
	}
	
}
