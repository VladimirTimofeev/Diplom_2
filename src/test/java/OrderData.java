public class OrderData {

    public static Order correctHashOrder() {
        String[] ingredients = {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa70"};
        return new Order(ingredients);
    }

    public static Order incorrectHashOrder() {
        String[] ingredients = {"61c0c5a71d1f82001bdaaa"};
        return new Order(ingredients);
    }

    public static Order withoutIngredient() {
        String[] ingredients = new String[3];
        return new Order(ingredients);
    }
}
