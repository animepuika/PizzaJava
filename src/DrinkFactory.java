public class DrinkFactory {
    public static Drinks create(DrinkType type) {
        switch (type) {
            case COLA:  return new Drinks("Cola", 2.0);
            case FANTA: return new Drinks("Fanta", 2.0);
            case WATER: return new Drinks("Water", 1.5);
            case JUICE: return new Drinks("Juice", 2.5);
            default: throw new IllegalArgumentException("Unknown drink type: " + type);
        }
    }
}
