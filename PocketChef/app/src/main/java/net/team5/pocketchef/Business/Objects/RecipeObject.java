package net.team5.pocketchef.Business.Objects;

import java.util.ArrayList;

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
    private final int recipeID;
    private final String recipeName;
    private final Category recipeCategory;
    private final ArrayList<String> recipeInstructions;
    private final ArrayList<Ingredient> recipeIngredients;

    /********************************************************
     * constructors
     ********************************************************/
    public RecipeObject(int recipeID, String recipeName, Category recipeCategory, ArrayList<String> recipeInstructions, ArrayList<Ingredient> recipeIngredients) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeCategory = recipeCategory;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;

        RecipeObject.idCounter++;
    }

    public RecipeObject(String recipeName, Category recipeCategory, ArrayList<String> recipeInstructions, ArrayList<Ingredient> recipeIngredients) {
        this.recipeID = RecipeObject.idCounter;
        this.recipeName = recipeName;
        this.recipeCategory = recipeCategory;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;

        RecipeObject.idCounter++;
    }

    /********************************************************
     * instance methods (accessors, toString, hashcode, equals)
     ********************************************************/
    public int getRecipeId() {
        return this.recipeID;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public Category getRecipeCategory() {
        return this.recipeCategory;
    }

    public ArrayList<String> getRecipeInstructions() {
        return this.recipeInstructions;
    }

    public ArrayList<Ingredient> getRecipeIngredients() {
        return this.recipeIngredients;
    }

}