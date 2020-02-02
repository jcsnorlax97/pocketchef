package net.team5.pocketchef.Database;

import net.team5.pocketchef.objects.Recipe.Recipe;

import java.util.ArrayList;

/**
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 1
 * John Hiebert
 * Feb 2 2020
 *
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
