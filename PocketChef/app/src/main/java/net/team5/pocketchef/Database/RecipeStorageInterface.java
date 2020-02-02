package net.team5.pocketchef.Database;

import net.team5.pocketchef.objects.Recipe.Recipe;

import java.util.ArrayList;

/**
 * the interface for the database
 *
 * searchName: Method for searching for recipe(s) (can return a list if recipes have identical names)
 *
 * searchCategory: Method for searching for recipe(s) that are equal to the categoryType
 *
 * addRecipe: Add a recipe to the database
 * */
public interface RecipeStorageInterface {
    public ArrayList<Recipe> searchName(String recipeName);
    public ArrayList<Recipe> searchCategory(String categoryType);

    public void addRecipe(Recipe newRecipe);
}
