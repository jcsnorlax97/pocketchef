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


    /**
     * Note: calling get is required twice as recipes and categories point to each other
     * The initial getRecipes call sets all its Categories to Null, the second fixes such problems
     */
    public void setUp()
    {
        ingredients = ingredientHandler.getIngredients();

        /** Set initial recipes which will be overwritten **/
        recipes = recipeHandler.getRecipes();
        categories = categoryHandler.getCategories();

        /** Override**/
        recipes = recipeHandler.getRecipes();
        categories = categoryHandler.getCategories();

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
            if (currRecipe.getRecipeId() == recipeID)
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
            categoryHandler.appendRecipeList(recipe.getRecipeCategory(), recipe);
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

    /** return a list of recipes that contains the name query provided **/
    public ArrayList<RecipeObject> searchRecipes(String searchQuery) {
        ArrayList<RecipeObject> toReturn = new ArrayList<>();

        for (int x = 0; x < recipes.size(); x++)
        {
            RecipeObject currRecipe = recipes.get(x);
            if (currRecipe.getRecipeName().toLowerCase().contains(searchQuery.toLowerCase()))
                toReturn.add(currRecipe);
        }

        return toReturn;
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

        /** check if null, because during start up categories is null **/
        if (categories == null) {
            return null;
        }

        for (int x = 0; x < categories.size(); x++)
        {
            Category currCategory = categories.get(x);
            if (currCategory.getCategoryName().toLowerCase().equals(categoryName.toLowerCase()))
            {
                toReturn = currCategory;
                break;
            }
        }

        return toReturn;
    }

    /** get recipes from the DB that matches the category in the string provided **/
    public ArrayList<RecipeObject> searchCategories(String searchQuery) {
        ArrayList<Category> matching = new ArrayList<Category>();

        /**find the categories that match the searchQuery **/
        for (int x = 0; x < categories.size(); x++)
        {
            Category currCategory = categories.get(x);
            if (currCategory.getCategoryName().toLowerCase().contains(searchQuery.toLowerCase()))
            {
                matching.add(currCategory);
            }
        }

        ArrayList<RecipeObject> toReturn = new ArrayList<RecipeObject>();
        /** Now that we have the categories, go through and get all the recipes **/
        for(int x = 0; x < matching.size(); x++)
        {
            toReturn.addAll(matching.get(x).getRecipeList());
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
            if (currIngredient.getIngredientName().toLowerCase().equals(ingredientName.toLowerCase()))
            {
                toReturn = currIngredient;
                break;
            }
        }

        return toReturn;
    }

    /** Get ingredient from the DB that matches name provded **/
    public ArrayList<RecipeObject> searchIngredients(String searchQuery) {
        ArrayList<RecipeObject> toReturn = new ArrayList<>();

        /** Go through all the recipes **/
        for (int x = 0; x < recipes.size(); x++)
        {
            RecipeObject currRecipe = recipes.get(x);
            ArrayList<Ingredient> currIngredients = currRecipe.getRecipeIngredients();

            /** Of the current recipe object go through all the ingredients and check if they match the seachQuery **/
            for(int i = 0; i < currIngredients.size(); i++)
            {
                Ingredient currIngredient = currIngredients.get(i);

                if (currIngredient.getIngredientName().toLowerCase().contains(searchQuery.toLowerCase()))
                {
                    toReturn.add(currRecipe);
                    break;
                }
            }
        }

        return toReturn;
    }

    /**
     * @param searchString The string that will be used to search the recipe names or categories or ingredients
     * @param searchCategories boolean that determines if we should be searching recipe categories (true = cat, false = names)
     * @param searchIngredients the boolean that determines if we should be searching recipe ingredients (true = ingred, false = names)
     *                          NOTE: if both searchCategories and searchIngredients are true we use only categories for searching
     *                          it is up to the caller to ensure that both are not true
     * @return a list of recipes that match the search query
     * */
    public ArrayList<RecipeObject> search(String searchString, boolean searchCategories, boolean searchIngredients)
    {
        /** check to see if we should be looking at the recipe categories, ingredients or names **/
        if(searchCategories)
        {
            return searchCategories(searchString);
        }
        else if(searchIngredients)
        {
            return searchIngredients(searchString);
        }
        else{
            return searchRecipes(searchString);
        }
    }
}
