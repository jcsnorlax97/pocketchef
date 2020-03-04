package net.team5.pocketchef.Business.Objects;

import net.team5.pocketchef.Business.Objects.Recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Category {

    /********************************************************
     * instance variables
     ********************************************************/
    private final String categoryName;    // MUTATION IS NOT ALLOWED, NAME IS TREATED AS ID
    private List<Recipe> recipeList;

    /********************************************************
     * instance methods
     ********************************************************/
    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.recipeList = new ArrayList<>();
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
    public String getCategoryName() {
        return this.categoryName;
    }

    public List<Recipe> getRecipeList() {
        return this.recipeList;
    }

    public String toString() {
        return String.format("Student: %s %s [%s]", this.categoryName, this.recipeList.toString());
    }
}
