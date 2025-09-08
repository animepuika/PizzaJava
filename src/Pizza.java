import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {
    private final String base;     // Thin, Thick, GlutenFree
    private final String size;     // Small, Medium, Large
    private final List<String> toppings;
    private final boolean extraCheese;

    private Pizza(String base, String size, List<String> toppings, boolean extraCheese) {
        this.base = base;
        this.size = size;
        this.toppings = Collections.unmodifiableList(new ArrayList<>(toppings));
        this.extraCheese = extraCheese;
    }

    public static class Builder {
        private String base = "Thin";
        private String size = "Medium";
        private final List<String> toppings = new ArrayList<>();
        private boolean extraCheese = false;

        public Builder base(String base) { this.base = base; return this; }
        public Builder size(String size) { this.size = size; return this; }
        public Builder addTopping(String topping) { this.toppings.add(topping); return this; }
        public Builder extraCheese(boolean on) { this.extraCheese = on; return this; }

        public Pizza build() { return new Pizza(base, size, toppings, extraCheese); }
    }

    @Override
    public String toString() {
        return "{base='" + base + "', size='" + size + "', toppings=" + toppings + ", extraCheese=" + extraCheese + "}";
    }
}



