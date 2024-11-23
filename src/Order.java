
import java.util.ArrayList;
import java.util.List;

public class Order implements IOrder {
	
	private List<Customer> customers;
	private String message;
	private boolean changed;
	private final Object MUTEX= new Object();
	
	 private int orderNumber;
     private String customerName;
     private String pizzaName;
     private String pizzaType;
     private String toppName;
     private String baseType;
     private String sidesName;
     private String drinksName;
     private String location;
	
	
	public Order(int OrderNumber, String CustomerName, String PizzaName, String PizzaType, String ToppName, String BaseType, String SidesName, String DrinksName, String Location) {
		 orderNumber = OrderNumber;
         customerName = CustomerName;
         pizzaName = PizzaName;
         pizzaType = PizzaType;
         toppName = ToppName;
         baseType = BaseType;
         sidesName = SidesName;
         drinksName = DrinksName;
         location = Location;
	}
	
	public int GetOrderNumber() {
		return orderNumber;
	}
	public String GetCustomerName() {
		return customerName;
	}
	
	public String GetPizzaName() {
		return pizzaName;
	}
	
	public String GetPizzaType() {
		return pizzaType;
	}
	
	public String GetPizzaTopping() {
		return toppName;
	}
	
	public String GetPizzaSides() {
		return sidesName;
	}
	
	public String GetPizzaBase() {
		return baseType;
	}
	
	public String GetDrinks() {
		return drinksName;
	}
	
	public String GetBranch() {
		return location;
	}
	
	public String toString() {
		return GetCustomerName() + " you have ordered a " + GetPizzaType() + GetPizzaName() + " topped with" + GetPizzaTopping() + " on a " + GetPizzaBase() + " base with " + GetPizzaSides() + " and a bottle of " + GetDrinks() + " , you have successfully ordered from our " + GetBranch() + " branch";
	} 

	public Order() {
		this.customers = new ArrayList<>();
	}
	
	public Order(OrderBuilder builder) {
		this.orderNumber = builder.orderNumber;
		this.customerName = builder.customerName;
		this.pizzaName = builder.pizzaName;
		this.pizzaType = builder.pizzaType;
		this.toppName = builder.toppName;
		this.baseType = builder.baseType;
		this.sidesName = builder.sidesName;
		this.drinksName = builder.drinksName;
		this.location = builder.location;
	}
	
	public static class OrderBuilder{
		 private int orderNumber;
		 private String customerName;
	     private String pizzaName;
	     private String pizzaType;
	     private String toppName;
	     private String baseType;
	     private String sidesName;
	     private String drinksName;
	     private String location;
	     
	     
	     
	     public OrderBuilder(int OrderNumber, String CustomerName, String PizzaName, String PizzaType, String ToppName, String BaseType, String SidesName, String DrinksName, String Location) {
			// TODO Auto-generated constructor stub
	    	 orderNumber = OrderNumber;
	         customerName = CustomerName;
	         pizzaName = PizzaName;
	         pizzaType = PizzaType;
	         toppName = ToppName;
	         baseType = BaseType;
	         sidesName = SidesName;
	         drinksName = DrinksName;
	         location = Location;
		}



		public Order constructOrder() {
	    	 return new Order(this);
	     }
	}
	
	
	
	// observer pattern methods implemented from IOrder interface
	@Override
	public void placeOrder(Customer cus) {
		// TODO Auto-generated method stub
		if(cus == null) throw new NullPointerException("Null Customer");
		synchronized (MUTEX) {
		if(!customers.contains(cus)) customers.add(cus);
		
	}
	}

	@Override
	public void deleteOrder(Customer cus) {
		// TODO Auto-generated method stub
		synchronized (MUTEX) {
			customers.remove(cus);
			}
	}

	@Override
	public void notifyCustomers() {
		// TODO Auto-generated method stub
		
		List<Customer> CustomersLocal = null;
		
		synchronized (MUTEX) {
			if (!changed)
				return;
			CustomersLocal = new ArrayList<>(this.customers);
			this.changed=false;
		}
		for (Customer cus : CustomersLocal) {
			cus.updateOrder();
		}
		
	}

	@Override
	public Object getOrderUpdate(Customer cus) {
		// TODO Auto-generated method stub
		return this.message;
	}
	
	public void postMessage(String msg){
		System.out.println("Message Posted to Order:"+msg);
		this.message=msg;
		this.changed=true;
		notifyCustomers();
	}

}
