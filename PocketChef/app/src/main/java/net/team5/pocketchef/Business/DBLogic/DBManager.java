package net.team5.pocketchef.Business.DBLogic;

import net.team5.pocketchef.Application.Services;
import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.Database.IngredientPersistence;
import net.team5.pocketchef.Database.RecipePersistence;

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
    public DBManager()
    {
        recipeHandler = Services.getRecipePersistence();
        categoryHandler = Services.getCategoryPersistence();
        ingredientHandler = Services.getIngredientPersistence();
    }

    /********************************************************
     * Builder, to call after constructor (to ensure instantiation of DBManager)
     ********************************************************/

    public void setUp()
    {
        recipes = recipeHandler.getRecipes();
        categories = categoryHandler.getCategories();
        ingredients = ingredientHandler.getIngredients();
    }

    /********************************************************
    * Recipe Methods
    ********************************************************/

    /** return a list of recipes that match the name provided **/
    public ArrayList<RecipeObject> getRecipes(String recipeName) {
       ArrayList<RecipeObject> toReturn = new ArrayList<>();

        /** check if input is valid **/
        if(recipeName.equals(""))
            return toReturn;

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
        RecipeObject toReturn = null;

        /** check if input is valid **/
        if(recipe == null)
            return toReturn;

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
            return toReturn;
        }
    }

    /** delete a recipe from the DB **/
    public void deleteRecipe(RecipeObject recipe) {

        /** check if input is valid **/
        if(recipe == null)
            return;

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
        Category toReturn = null;

        /** check if input is valid **/
        if(category == null)
            return toReturn;

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
            return toReturn;
        }
    }

    /** delete a Category from DB **/
    public void deleteCategory(Category category) {

        /** check if input is valid **/
        if(category == null)
            return;

        try {
            categoryHandler.deleteCategory(category);
            categories.remove(category);

            /** change all recipes category after deleting category **/
            ArrayList<RecipeObject> recipes = category.getRecipeList();
            for(int x = 0; x < recipes.size(); x++)
            {
                recipes.get(x).setRecipeCategory(null);
            }
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the it was not deleted
             **/
            e.printStackTrace(System.out);
        }
    }

    /** add a recipe to the Category provided **/
    public Category appendRecipeList(Category category, RecipeObject recipe) {
        Category toReturn = null;

        /** check if input is valid **/
        if(category == null)
            return toReturn;
        if(recipe == null)
            return toReturn;

        try {
            toReturn = categoryHandler.appendRecipeList(category, recipe);
            return toReturn;
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the null return and print a custom error
             **/
            e.printStackTrace(System.out);
            return toReturn;
        }
    }

    /** add a recipe to the Category provided **/
    public Category deleteRecipe(Category category, RecipeObject recipe) {
        Category toReturn = null;

        /** check if input is valid **/
        if(category == null)
            return toReturn;
        if(recipe == null)
            return toReturn;

        try {
            toReturn = categoryHandler.deleteRecipe(category, recipe);
            return toReturn;
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the null return and print a custom error
             **/
            e.printStackTrace(System.out);
            return toReturn;
        }
    }

    /** get the entire list of Categories **/
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /** get a Category from the DB that matches name provided **/
    public Category getCategory(String categoryName) {
        Category toReturn = null;

        /** check if input is valid **/
        if(categoryName.equals(""))
            return toReturn;


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
        Ingredient toReturn = null;

        /** check if input is valid **/
        if(ingredient == null)
            return toReturn;

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
            return toReturn;
        }
    }

    /** delete ingredient from the DB **/
    public void deleteIngredient(Ingredient ingredient) {

        /** check if input is valid **/
        if(ingredient == null)
            return;

        try {
            ingredientHandler.deleteIngredient(ingredient);
            ingredients.remove(ingredient);
        } catch (Exception e)
        {
            /** The user does not need to see this error
             * -Whoever made the call will see the null return and print a custom error
             **/
            e.printStackTrace(System.out);
        }
    }

    /** get ingredient from the DB that matches name provded **/
    public Ingredient getIngredient(String ingredientName) {
        Ingredient toReturn = null;

        /** check if input is valid **/
        if(ingredientName.equals(""))
            return toReturn;

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
