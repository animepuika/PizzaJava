import java.util.*;

public class MainApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // --- 1) Build a Pizza (Builder) ---
        System.out.println("=== Build Your Pizza ===");

        String base = pickOne(in, "Choose base", new String[]{"Thin", "Thick", "GlutenFree"});
        String size = pickOne(in, "Choose size", new String[]{"Small", "Medium", "Large"});

        Pizza.Builder pb = new Pizza.Builder().base(base).size(size);

        // toppings: comma-separated, optional
        System.out.print("Enter toppings (comma-separated, or leave empty): ");
        String toppingsLine = in.nextLine().trim();
        if (!toppingsLine.isEmpty()) {
            for (String t : toppingsLine.split(",")) {
                String tt = t.trim();
                if (!tt.isEmpty()) pb.addTopping(tt);
            }
        }

        boolean extraCheese = yesNo(in, "Extra cheese? (y/n): ");
        if (extraCheese) pb.extraCheese(true);

        Pizza pizza = pb.build();

        // --- 2) Add a Drink (Factory Method) ---
        System.out.println("\n=== Choose a Drink (optional) ===");
        Drinks drink = null;
        if (yesNo(in, "Add a drink? (y/n): ")) {
            DrinkType[] drinkTypes = DrinkType.values();
            String[] drinkNames = Arrays.stream(drinkTypes).map(Enum::name).toArray(String[]::new);
            String chosen = pickOne(in, "Pick a drink", drinkNames);
            DrinkType dt = DrinkType.valueOf(chosen);
            drink = DrinkFactory.create(dt);
        }

        // --- 3) Add Sides (Decorator-ready objects) ---
        System.out.println("\n=== Choose Sides (optional, you can add multiple) ===");
        List<Sides> availableSides = Arrays.asList(
            new Sides("Garlic Bread", 3.0),
            new Sides("Fries", 2.5),
            new Sides("Salad", 2.0)
        );

        List<Sides> chosenSides = new ArrayList<>();
        if (yesNo(in, "Add sides? (y/n): ")) {
            while (true) {
                String[] sideMenu = new String[availableSides.size() + 1];
                for (int i = 0; i < availableSides.size(); i++) {
                    Sides s = availableSides.get(i);
                    sideMenu[i] = s.getName() + " (\u20AC" + s.getPrice() + ")";
                }
                sideMenu[sideMenu.length - 1] = "Done adding sides";
                int pick = pickIndex(in, "Pick a side (or Done)", sideMenu);
                if (pick == sideMenu.length - 1) break; // Done
                chosenSides.add(availableSides.get(pick));
            }
        }

        // --- 4) Compose Order Item (Decorator) ---
        OrderItem item = new BasicPizza(pizza);
        if (drink != null) item = new WithDrink(item, drink);
        for (Sides s : chosenSides) {
            item = new WithSide(item, s);
        }

        System.out.println("\n[Summary] " + item.getDescription());
        System.out.println("[Subtotal] \u20AC" + item.getCost());

        // --- 5) Who is ordering? (Observer + Strategy) ---
        System.out.println("\n=== Customer Details ===");
        System.out.print("Customer name: ");
        String customerName = in.nextLine().trim();
        Customer customer = new Customer(customerName);

        boolean isStudent = yesNo(in, "Apply student discount? (y/n): ");
        Checkout checkout = new Checkout();
        if (isStudent) checkout.setStrategy(new StudentDiscountPricing()); // Strategy

        double total = checkout.total(item);

        // --- 6) Place the order (Observer) ---
        Order order = new Order();
        customer.setOrder(order);
        order.register(customer);

        String details = item.getDescription() + " | Total: \u20AC" + total;
        order.placeOrder(details);

        System.out.println("\nOrder placed. Thanks!");
    }

    // ----------------- helpers -----------------

    private static boolean yesNo(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no")) return false;
            System.out.println("Please enter y or n.");
        }
    }

    private static String pickOne(Scanner in, String title, String[] options) {
        int idx = pickIndex(in, title, options);
        return options[idx];
    }

    private static int pickIndex(Scanner in, String title, String[] options) {
        System.out.println(title + ":");
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ") " + options[i]);
        }
        while (true) {
            System.out.print("Enter number 1-" + options.length + ": ");
            String s = in.nextLine().trim();
            try {
                int n = Integer.parseInt(s);
                if (n >= 1 && n <= options.length) return n - 1;
            } catch (NumberFormatException ignore) {}
            System.out.println("Invalid choice, try again.");
        }
    }
}





