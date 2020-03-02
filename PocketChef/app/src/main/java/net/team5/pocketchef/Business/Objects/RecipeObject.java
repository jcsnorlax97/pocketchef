package net.team5.pocketchef.Business.Objects;

import java.util.List;

/**
 * This RecipeObject class is used for representing the RecipeObject.
 */
public class RecipeObject {

    /********************************************************
     * class variables
     ********************************************************/
    private static int idCounter = 0; // to be used for unique id.

    /********************************************************
     * instance variables
     ********************************************************/
    private final String recipeId;
    private final String recipeName;
    private final Category recipeCategory;
    private final String recipeInstruction;
    private final List<Ingredient> recipeIngredients;

    /********************************************************
     * constructors
     ********************************************************/
    public RecipeObject(String recipeName, Category recipeCategory, String recipeInstruction, List<Ingredient> recipeIngredients) {
        this.recipeId = Integer.toString(RecipeObject.idCounter);
        this.recipeName = recipeName;
        this.recipeCategory = recipeCategory;
        this.recipeInstruction = recipeInstruction;
        this.recipeIngredients = recipeIngredients;

        RecipeObject.idCounter++;
    }

    /********************************************************
     * instance methods (accessors, toString, hashcode, equals)
     ********************************************************/
    public String getRecipeId() {
        return this.recipeId;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public Category getRecipeCategory() {
        return this.recipeCategory;
    }

    public String getRecipeInstruction() {
        return this.recipeInstruction;
    }

    public List<Ingredient> getRecipeIngredients() {
        return this.recipeIngredients;
    }

}