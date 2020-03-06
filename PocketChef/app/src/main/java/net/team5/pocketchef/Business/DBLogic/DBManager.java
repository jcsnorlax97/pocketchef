package net.team5.pocketchef.Business.DBLogic;

import net.team5.pocketchef.Application.Services;
import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.Database.IngredientPersistence;
import net.team5.pocketchef.Database.RecipePersistence;
import net.team5.pocketchef.Database.hsqldb.CategoryHandler;
import net.team5.pocketchef.Database.hsqldb.IngredientHandler;
import net.team5.pocketchef.Database.hsqldb.RecipeHandler;

import java.util.ArrayList;

/********************************************************
* Class Description:
* This class creates the DBHandlers to be used globally
* across the app to set/get stuff from the DB
********************************************************/

public class DBManager {

    /********************************************************
    * Private Class Variables
    ********************************************************/
    private static RecipePersistence recipeHandler;
    private static CategoryPersistence categoryHandler;
    private static IngredientPersistence ingredientHandler;

    private static ArrayList<RecipeObject> recipes;
    private static ArrayList<Category> categories;
    private static ArrayList<Ingredient> ingredients;

    /********************************************************
    * Constructor
    ********************************************************/
    //TODO: add/get the correct dbPath
    public DBManager()
    {
        recipeHandler = Services.getRecipePersistence();
        categoryHandler = Services.getCategoryPersistence();
        ingredientHandler = Services.getIngredientPersistence();

        recipes = recipeHandler.getRecipes();
//        categories = categoryHandler.getCategories();     // there are issues inside of the handler; will fix it in iteration 3.
        ingredients = ingredientHandler.getIngredients();
    }

    /********************************************************
    * Recipe Methods
    ********************************************************/

    /** return a list of recipes that match the name provided **/
    public ArrayList<RecipeObject> getRecipes(String recipeName) {
       ArrayList<RecipeObject> toReturn = new ArrayList<>();

       for (int x = 0; x < recipes.size(); x++)
       {
           RecipeObject currRecipe = recipes.get(x);
           if (currRecipe.getRecipeName().equals(recipeName))
               toReturn.add(currRecipe);
       }

       return toReturn;
    }

    /** return the entire list of recipes **/
    public ArrayList<RecipeObject> getRecipes() {
        return recipes;
    }

    /** return a recipe that matches the ID provided **/
    public RecipeObject getRecipe(int recipeID) {
        RecipeObject toReturn = null;

        for (int x = 0; x < recipes.size(); x++)
        {
            RecipeObject currRecipe = recipes.get(x);
            if (currRecipe.getRecipeId() == (recipeID))
            {
                toReturn = currRecipe;
                break;
            }
        }

        return toReturn;
    }

    /** add a recipe to the DB **/
    public RecipeObject addRecipe(RecipeObject recipe) {
        // NOT REDUNDANT, DB could throw exception and never return
        RecipeObject toReturn = null;

        try {
            toReturn = recipeHandler.addRecipe(recipe);
            recipes.add(recipe);
            return toReturn;
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the null return and print a custom error
             **/
            e.printStackTrace(System.out);
            return null;
        }
    }

    /** delete a recipe from the DB **/
    public void deleteRecipe(RecipeObject recipe) {
        try {
            recipeHandler.deleteRecipe(recipe);
            recipes.remove(recipe);
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the recipe has not been removed from the list
             * and will print a custom error
             **/
            e.printStackTrace(System.out);
        }
    }

    /********************************************************
    * Category Methods
    ********************************************************/

    /** add a Category to the DB **/
    public Category createCategory(Category category) {
        // NOT REDUNDANT, DB could throw exception and never return
        Category toReturn = null;

        try {
            toReturn = categoryHandler.createCategory(category);
            categories.add(category);
            return toReturn;
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the null return and print a custom error
             **/
            e.printStackTrace(System.out);
            return null;
        }
    }

    //TODO: decide on if Arrays will be continued to be used (Iteration 3) before using this
    //FIXME: I DO NOT WORK YET SO PLEASE DON'T USE ME
    /** add a recipe to the Category provided **/
    public Category appendRecipeList(Category category, RecipeObject recipe) {
        // NOT REDUNDANT, DB could throw exception and never return
        Category toReturn = null;

        try {
            toReturn = categoryHandler.appendRecipeList(category, recipe);
            category.addRecipe(recipe);
            return toReturn;
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the null return and print a custom error
             **/
            e.printStackTrace(System.out);
            return null;
        }
    }

    /** get the entire list of Categories **/
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /** get a Category from the DB that matches name provided **/
    public Category getCategory(String categoryName) {
        Category toReturn = null;

        for (int x = 0; x < categories.size(); x++)
        {
            Category currCategory = categories.get(x);
            if (currCategory.getCategoryName().equals(categoryName))
            {
                toReturn = currCategory;
                break;
            }
        }

        return toReturn;
    }

    /********************************************************
    * Ingredient Methods
    ********************************************************/

    /** add ingredient to the DB **/
    public Ingredient addIngredient(Ingredient ingredient) {
        // NOT REDUNDANT, DB could throw exception and never return
        Ingredient toReturn = null;

        try {
            toReturn = ingredientHandler.addIngredient(ingredient);
            ingredients.add(ingredient);
            return toReturn;
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the null return and print a custom error
             **/
            e.printStackTrace(System.out);
            return null;
        }
    }

    /** get ingredient from the DB that matches name provded **/
    public Ingredient getIngredient(String ingredientName) {
        Ingredient toReturn = null;

        for (int x = 0; x < ingredients.size(); x++)
        {
            Ingredient currIngredient = ingredients.get(x);
            if (currIngredient.getIngredientName().equals(ingredientName))
            {
                toReturn = currIngredient;
                break;
            }
        }

        return toReturn;
    }
}
