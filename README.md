Repository link https://github.com/animepuika/PizzaPatterns

I worked alone because I am a 3 year student doing a minor right now.

Application to order pizzas.

Design Patterns that are being used:

1. Observer Pattern
2. Bridge Pattern
3. Builder Pattern

Observer Pattern: The classes involved are Customer, Order, and MainApp.

The Observer pattern allows an object to log in and log out and receive updates from another object (subject) when the object's state changes. 
In the MainApp class, after creating a new order, the program creates her Customer object containing the customer's name and location. 
This Customer object is then registered as an observer of the Order object using the placeOrder() method. When an order is placed or withdrawn, the Order object calls the updateOrder() method to notify the observer (in this case her Customer object). 
The Customer class implements the Observer interface and defines an update() method to handle updates from the Order object.

Bridge pattern: The classes involved are UserTypes, User, CustomerView, and MainApp.

The Bridge pattern separates an abstraction from its implementation, allowing each to be modified independently. 
The UserTypes interface acts as an abstraction and defines methods such as accessUser(). 
The User class represents a sophisticated abstraction using instances of UserType. CustomerView is an implementation of UserTypes that provides specific functionality for customer-related views. 
When a user selects her Customer option in her MainApp class, a new User object with a CustomerView instance is created, demonstrating separation between the abstraction and its implementation.

Builder pattern: The classes involved are Order, OrderBuilder, and MainApp.

The Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to be used to create different representations. 
The Order class represents the complex object that is created, containing various attributes of the order such as order number, customer name, pizza details, etc. 
The OrderBuilder class provides a fluid interface for incrementally building Order objects. In the MainApp class, when a user decides to place an order, a new Order object is created using an OrderBuilder and the required parameters. 
OrderBuilder encapsulates the construction process, making it easy to create Order objects in a variety of configurations while ensuring immutability of the Order class itself.