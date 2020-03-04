package net.team5.pocketchef.Business.Objects;

public class Ingredient {

    /********************************************************
     * instance variables
     ********************************************************/
    private final String ingredientName;  // MUTATION IS NOT ALLOWED

    /********************************************************
     * instance methods
     ********************************************************/
    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /********************************************************
     * instance methods (accessors)
     ********************************************************/
    public String getIngredientName() {
        return this.ingredientName;
    }
}
