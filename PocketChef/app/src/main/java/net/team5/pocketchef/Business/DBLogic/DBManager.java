package net.team5.pocketchef.Business.DBLogic;

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

public class DBManager implements RecipePersistence, IngredientPersistence, CategoryPersistence {

    /********************************************************
    * Global Variables
    ********************************************************/
    private static RecipeHandler recipeHandler;
    private static CategoryHandler categoryHandler;
    private static IngredientHandler ingredientHandler;

    private static ArrayList<RecipeObject> recipes;
    private static ArrayList<Category> categories;
    private static ArrayList<Ingredient> ingredients;

    /********************************************************
    * Constructor
    ********************************************************/
    //TODO: add/get the correct dbPath
    public DBManager()
    {
        recipeHandler = new RecipeHandler("");
        categoryHandler = new CategoryHandler("");
        ingredientHandler = new IngredientHandler("");

        recipes = recipeHandler.getRecipes();
        categories = categoryHandler.getCategories();
        ingredients = ingredientHandler.getIngredients();
    }

    /********************************************************
    * Recipe Methods
    ********************************************************/

    @Override
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

    @Override
    /** return the entire list of recipes **/
    public ArrayList<RecipeObject> getRecipes() {
        return recipes;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
    @Override
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

    @Override
    /** get the entire list of Categories **/
    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
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

    @Override
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

    @Override
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
