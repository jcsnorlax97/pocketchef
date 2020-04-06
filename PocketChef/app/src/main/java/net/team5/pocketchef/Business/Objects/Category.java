package net.team5.pocketchef.Business.Objects;

import net.team5.pocketchef.Business.Objects.RecipeObject;

import java.util.ArrayList;
import java.util.List;

public class Category {

    /********************************************************
     * instance variables
     ********************************************************/
    private final String categoryName;    // MUTATION IS NOT ALLOWED, NAME IS TREATED AS ID
    private ArrayList<RecipeObject> recipeList;

    /********************************************************
     * instance methods
     ********************************************************/
    public Category(String categoryName, ArrayList<RecipeObject> recipeList) {
        this.categoryName = categoryName;
        if (recipeList != null)
            this.recipeList = recipeList;
        else
            this.recipeList = new ArrayList<>();
    }

    /********************************************************
     * instance methods
     ********************************************************/

    // ASSUMPTION: VALIDATION OF RECIPE IS HANDLED BY BUSINESS LAYER
    public void appendRecipeList(RecipeObject recipe) {
        this.recipeList.add(recipe);
    }

    /********************************************************
     * instance methods (accessors & toString)
     ********************************************************/
    public String getCategoryName() {
        return this.categoryName;
    }

    public ArrayList<RecipeObject> getRecipeList() {
        return this.recipeList;
    }

    public void deleteRecipe(RecipeObject recipe)
    {
        recipeList.remove(recipe);
    }

    public String toString() {
        return this.categoryName;
    }
}
