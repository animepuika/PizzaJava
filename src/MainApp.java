
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class MainApp {

	// Pizza ordering application using Bridge, Observer and Builder software Design Patterns
	
	// this program demonstrates a simple usage of customers creating an order through user input
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userInput = new Scanner(System.in);
		String userType = "";
		

		while (!userType.equals("z")) {
		
			System.out.println("are you a customer or employee: ");
			userType = userInput.nextLine();
			
			if (userType.equals("customer")) {
				
			UserTypes newCustomer = new User(new CustomerView());
			
			newCustomer.accessUser();
			
		
			
			OrderingSystem();
			}
			else {
				System.out.println("incorrect usertype");
			}
			
			
			
		}

	}
	
	public static void OrderingSystem() {
		Scanner userInput = new Scanner(System.in);
		
	// hashmaps to act as data dictionaries for pizza, pizzatype, sides, drinks, pizzabase, toppings (string, float)
	// arraylist for pizza branch location
		
		HashMap<String, Float> pizzaSelection = new HashMap<>();
		pizzaSelection.put("margherita", 10.00f);
		pizzaSelection.put("pepperoni", 11.00f);
		pizzaSelection.put("meat feast", 12.74f);
		pizzaSelection.put("vegetarian", 8.00f);
		pizzaSelection.put("hawaiian", 8.68f);
		pizzaSelection.put("bbq chicken", 11.75f);
		
		HashMap<String, Float> pizzaTypeSelection = new HashMap<>();
		pizzaTypeSelection.put("neopoliton", 5.00f);
		pizzaTypeSelection.put("deep dish", 6.00f);
		pizzaTypeSelection.put("new york style", 7.74f);
		pizzaTypeSelection.put("greek", 8.00f);
		pizzaTypeSelection.put("sicilian", 8.68f);
		pizzaTypeSelection.put("calzone", 9.75f);
		
		HashMap<String, Float> toppingSelection = new HashMap<>();
		toppingSelection.put("sausage", 0.75f);
		toppingSelection.put("anchovies", 0.89f);
		toppingSelection.put("extra chedder cheese", 0.74f);
		
		
		HashMap<String, Float> baseSelection = new HashMap<>();
		baseSelection.put("small", 1.00f);
		baseSelection.put("medium", 2.00f);
		baseSelection.put("large", 3.00f);
		
		
		HashMap<String, Float> sideSelection = new HashMap<>();
		sideSelection.put("doughballs", 1.75f);
		sideSelection.put("chicken wings", 2.89f);
		sideSelection.put("garlic bread", 3.74f);
		
		HashMap<String, Float> drinkSelection = new HashMap<>();
		drinkSelection.put("coca cola", 1.50f);
		drinkSelection.put("water", 1.00f);
		drinkSelection.put("sprite", 1.30f);
		
		 ArrayList<String> locations = new ArrayList<String>();
		 locations.add("london");
		 locations.add("leeds");
		 locations.add("bath");
		 locations.add("bristol");
		 locations.add("manchester");
		// stores summary of order
		Object[] orderSummary = new Object[9];
		
		// stores prices of order components
		 ArrayList<Float> finalOrderPrice = new ArrayList<Float>();

		
		//ordering variables
		String empType;
        String name;
        String cusName;
        String usertype;
        String location;
        String pizzaName;
        String pizzaType;
        String pizzaBase;
        String topping;
        String sides;
        String drink;
        int orderNumber = 0;
        int orderCount = 0;
        String response = "";
        boolean placed = false;
        boolean completeOrder = false;
	
		
        System.out.println("Time to create your Order!");
    	
        
        orderNumber = orderNumber + 1;
        orderSummary[0] = orderNumber;
        
        System.out.println("what is your name: ");
        cusName = userInput.nextLine();
        // assigns name to array slot
        orderSummary[1] = cusName;
        
       // prompts user to input the branch they would like to order from
        System.out.println("what branch would you like to order from: ");
        location = userInput.nextLine();
        
        while(true) {
        	if (locations.contains(location)) {
        		System.out.println("location exists");
        		orderSummary[8] = location;
        		// new customer object created
        		
        		break;
        	}
        	
        	else {
        		System.out.println("location does not exists");
        		System.out.println("what branch would you like to order from: ");
                location = userInput.nextLine();
        	}
        }
        
        System.out.println("what pizza would you like: ");
        pizzaName = userInput.nextLine();
        
        while(true) {
        	if(pizzaSelection.containsKey(pizzaName)) {
        		System.out.println("pizza is availible");
        		// prints out value of inputted key
        		System.out.println("this will cost you £" + pizzaSelection.get(pizzaName));
        		orderSummary[2] = pizzaName;
        		// adds value from key in the hashmap into final order price arraylist
        		finalOrderPrice.add(pizzaSelection.get(pizzaName));
        		
        		
        		break;
        	}
        	else {
        		System.out.println("pizza is not on offer");
        		 System.out.println("what pizza would you like: ");
        	        pizzaName = userInput.nextLine();
        	}
        }
        
        System.out.println("what type of pizza would you like: ");
        pizzaType = userInput.nextLine();
        
        while(true) {
        	if(pizzaTypeSelection.containsKey(pizzaType)) {
        		System.out.println("pizza type"
        				+ " is availible");
        		System.out.println("this will cost you £" + pizzaTypeSelection.get(pizzaType));
        		orderSummary[3] = pizzaType;
        		finalOrderPrice.add(pizzaTypeSelection.get(pizzaType));
        		//  creates new pizza object
        		Pizza newPizza = new Pizza(pizzaName, pizzaType);
        		
        		break;
        	}
        	else {
        		System.out.println("pizza type is not on offer");
        		 System.out.println("what type of pizza would you like: ");
        	        pizzaType = userInput.nextLine();
        	}
        }
        
        System.out.println("what topping would you like: ");
        topping = userInput.nextLine();
        
        while(true) {
        	if(toppingSelection.containsKey(topping)) {
        		System.out.println("pizza topping is availible");
        		System.out.println("this will cost you £" + toppingSelection.get(topping));
        		orderSummary[4] = topping;
        		finalOrderPrice.add(toppingSelection.get(topping));
        		Toppings topp = new Toppings(topping);
        		
        		break;
        	}
        	else {
        		// if inputted topping does not exist
        		System.out.println("pizza topping is not on offer");
        		  System.out.println("what topping would you like: ");
        	        topping = userInput.nextLine();
        	}
        }
        
        System.out.println("what base size would you like: ");
        pizzaBase = userInput.nextLine();
        
        while(true) {
        	if(baseSelection.containsKey(pizzaBase)) {
        		
        		System.out.println("this will cost you £" + baseSelection.get(pizzaBase));
        		orderSummary[5] = pizzaBase;
        		finalOrderPrice.add(baseSelection.get(pizzaBase));
        		//created pizza base object
        		Base base = new Base(pizzaBase);
        		break;
        	}
        	else {
        	       System.out.println("what base size would you like: ");
        	        pizzaBase = userInput.nextLine();
        	      
        	}
        }
        
        System.out.println("what side would you like: ");
        sides = userInput.nextLine();
        
        while(true) {
        	if(sideSelection.containsKey(sides)) {
        		System.out.println("pizza topping is availible");
        		System.out.println("this will cost you £" + sideSelection.get(sides));
        		orderSummary[6] = sides;
        		finalOrderPrice.add(sideSelection.get(sides));
        		Sides side = new Sides(sides);
        		break;
        	}
        	else {
        		// if inputted side does not exist
        		  System.out.println("what side would you like: ");
        	        sides = userInput.nextLine();
        	}
        }
        
        System.out.println("what drink would you like: ");
        drink = userInput.nextLine();
        
        while(true) {
        	if(drinkSelection.containsKey(drink)) {
        		System.out.println("drink is availible");
        		System.out.println("this will cost you £" + drinkSelection.get(drink));
        		orderSummary[7] = drink;
        		finalOrderPrice.add(drinkSelection.get(drink));
        		Drinks newDrink = new Drinks(drink);
        		break;
        	}
        	else {
        		 System.out.println("drink is not availible");
        		  System.out.println("what drink would you like: ");
        	        drink = userInput.nextLine();
        	}
        }
        // shows summary of inputted items by the customer
        System.out.println(cusName + " here is your proposed order");
        
        for (var order : orderSummary) {
        	System.out.println(order);
        }
        
        // orderSum will total the full price of the customer's order
        float orderSum = 0; 
        
  
        for(int i = 0; i < finalOrderPrice.size(); i++)
            orderSum += finalOrderPrice.get(i);
        	System.out.println(" £"+ orderSum);       
		
        	//created order object
		Order newOrder = new Order();
		
		
		
		
		//observer pattern functions
		
		
		Customer newCustomer = new Customer(cusName, location);
		
		System.out.println("would you like to place your order");
		response = userInput.nextLine();
		if (response.equals("yes")) {
			// builder pattern function
			Order finalOrder = new Order.OrderBuilder(orderNumber, cusName, pizzaName, pizzaType, topping, pizzaBase, sides, drink, location).constructOrder();
			newOrder.placeOrder(newCustomer);
			newCustomer.setOrder(finalOrder);
			newOrder.postMessage("order has been placed");
			placed = true;
			
			newOrder.postMessage("order has been completed");
			
			// prints users order to screen
			System.out.println(finalOrder.toString());
			
			
			finalOrderPrice.clear();
			
			
			newCustomer.updateOrder();
			
			
			
		}
		
		else if (response.equals("no")) {
			newOrder.deleteOrder(newCustomer);
			newOrder.postMessage("order has been withdrawn");
			finalOrderPrice.clear();
			placed = false;
		}
	}
	}

	


