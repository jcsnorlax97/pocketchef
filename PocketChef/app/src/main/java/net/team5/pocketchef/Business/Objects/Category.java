package net.team5.pocketchef.Business.Objects;

import net.team5.pocketchef.Business.Objects.Recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Category {

    /********************************************************
     * class variables
     ********************************************************/
    private static int idCounter = 0; // to be used for unique id.

    /********************************************************
     * instance variables
     ********************************************************/
    private final String categoryId;    // MUTATION IS NOT ALLOWED
    private final String categoryName;  // MUTATION IS NOT ALLOWED
    private List<Recipe> recipeList;

    /********************************************************
     * instance methods
     ********************************************************/
    public Category(String categoryName) {
        this.categoryId = Integer.toString(Category.idCounter);
        this.categoryName = categoryName;
        this.recipeList = new ArrayList<>();

        // increment counter for next instance
        Category.idCounter++;
    }

    /********************************************************
     * instance methods
     ********************************************************/

    // ASSUMPTION: VALIDATION OF RECIPE IS HANDLED BY BUSINESS LAYER
    public void appendRecipeList(Recipe recipe) {
        this.recipeList.add(recipe);
    }

    /********************************************************
     * instance methods (accessors & toString)
     ********************************************************/
    public String getCategoryId() {
        return this.categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<Recipe> getRecipeList() {
        return this.recipeList;
    }

    public String toString() {
        return String.format("Student: %s %s [%s]", this.categoryId, this.categoryName, this.recipeList.toString());
    }
}
