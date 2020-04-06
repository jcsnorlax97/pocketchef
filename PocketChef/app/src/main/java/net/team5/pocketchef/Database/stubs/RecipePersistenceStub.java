package net.team5.pocketchef.Database.stubs;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.Database.IngredientPersistence;
import net.team5.pocketchef.Database.RecipePersistence;

import java.util.ArrayList;

public class RecipePersistenceStub implements RecipePersistence
{

    /********************************************************
     * Instance Variables
     ********************************************************/
    private ArrayList<RecipeObject> recipes;
    private CategoryPersistence categoryPersistence;
    private IngredientPersistence ingredientPersistence;


    /********************************************************
     * Constructors
     ********************************************************/

    // a bit coupling on CategoryPersistence & IngredientPersistence in constructor
    public RecipePersistenceStub(CategoryPersistence categoryPersistence, IngredientPersistence ingredientPersistence)
    {
        recipes = new ArrayList<RecipeObject>();
        this.categoryPersistence = categoryPersistence;
        this.ingredientPersistence = ingredientPersistence;

        // retrieve required category from faked category db.
        Category veganCategory = categoryPersistence.getCategory("Vegan");
        Category mexicanCategory = categoryPersistence.getCategory("Mexican");

        // retrieve required ingredients from faked ingredient db.
        Ingredient eggsIngredient = ingredientPersistence.getIngredient("Eggs");
        Ingredient saltIngredient = ingredientPersistence.getIngredient("Salt");
        Ingredient pepperIngredient = ingredientPersistence.getIngredient("Pepper");
        Ingredient aSliceOfBreadIngredient = ingredientPersistence.getIngredient("A slice of bread");
        Ingredient baconsIngredient = ingredientPersistence.getIngredient("Bacons");
        Ingredient onePercentSkimMilkIngredient = ingredientPersistence.getIngredient("1% skim milk");

        // create placeholder variables for create a new recipe.
        String recipeName;
        Category recipeCategory;
        ArrayList<String> singleRecipeInstructionList;
        ArrayList<Ingredient> singleRecipeIngredientList;

        // create & add (aka load from real db) the first vegan recipe
        recipeName = "(Faked) Fried Eggs";
        recipeCategory = veganCategory;
        singleRecipeInstructionList = new ArrayList<>();
        singleRecipeInstructionList.add("1. Whisk eggs.");
        singleRecipeInstructionList.add("2. Heat the pan with oil in high heat until the pan is hot enough.");
        singleRecipeInstructionList.add("3. Put eggs on the pan.");
        singleRecipeInstructionList.add("4. Add salt and pepper.");
        singleRecipeIngredientList = new ArrayList<>();
        singleRecipeIngredientList.add(eggsIngredient);
        singleRecipeIngredientList.add(saltIngredient);
        singleRecipeIngredientList.add(pepperIngredient);
        RecipeObject fakedFriedEggsRecipe = new RecipeObject(recipeName, recipeCategory, singleRecipeInstructionList, singleRecipeIngredientList);

        // create & add (aka load from real db) the first mexican recipe
        recipeName = "(Faked) Mexican Breakfast";
        recipeCategory = mexicanCategory;
        singleRecipeInstructionList = new ArrayList<>();
        singleRecipeInstructionList.add("1. Fry the bacons.");
        singleRecipeInstructionList.add("2. Fry the eggs.");
        singleRecipeInstructionList.add("3. Add salt and pepper onto the fried eggs.");
        singleRecipeInstructionList.add("4. Get the milk from the fridge.");
        singleRecipeInstructionList.add("5. Put them on the plate.");
        singleRecipeIngredientList = new ArrayList<>();
        singleRecipeIngredientList.add(baconsIngredient);
        singleRecipeIngredientList.add(eggsIngredient);
        singleRecipeIngredientList.add(saltIngredient);
        singleRecipeIngredientList.add(pepperIngredient);
        singleRecipeIngredientList.add(onePercentSkimMilkIngredient);
        RecipeObject fakedMexicanBreakfastRecipe = new RecipeObject(recipeName, recipeCategory, singleRecipeInstructionList, singleRecipeIngredientList);

        // create & add (aka load from real db) the second mexican recipe
        recipeName = "(Faked) Mexican Bread";
        recipeCategory = mexicanCategory;
        singleRecipeInstructionList = new ArrayList<>();
        singleRecipeInstructionList.add("1. Take the bread out.");
        singleRecipeInstructionList.add("2. Heat the bread until brown.");
        singleRecipeIngredientList = new ArrayList<>();
        singleRecipeIngredientList.add(aSliceOfBreadIngredient);
        RecipeObject fakedMexicanBreadRecipe = new RecipeObject(recipeName, recipeCategory, singleRecipeInstructionList, singleRecipeIngredientList);

        // add all created recipes into the faked Recipe DB.
        recipes.add(fakedFriedEggsRecipe);
        recipes.add(fakedMexicanBreakfastRecipe);
        recipes.add(fakedMexicanBreadRecipe);

        // update for the corresponding categories
        this.categoryPersistence.appendRecipeList(fakedFriedEggsRecipe.getRecipeCategory(), fakedFriedEggsRecipe);
        this.categoryPersistence.appendRecipeList(fakedMexicanBreakfastRecipe.getRecipeCategory(), fakedMexicanBreakfastRecipe);
        this.categoryPersistence.appendRecipeList(fakedMexicanBreadRecipe.getRecipeCategory(), fakedMexicanBreadRecipe);

        // no new ingredients are added so no need to add ingredients
    }

    /********************************************************
     * Instance Methods
     ********************************************************/

    /**
     * getRecipes [Done]
     *
     * Responsibilities:
     * - retrieve the whole list of recipes and return back to callers
     **/
    public ArrayList<RecipeObject> getRecipes()
    {
        return this.recipes;
    }

    /**
     * getRecipes [Done]
     *
     * Responsibilities:
     *  - Search ALL recipes having the target recipe name and return to callers
     *  - Remark: search based on recipe name
     **/
    public ArrayList<RecipeObject> getRecipes(String recipeName)
    {
        ArrayList<RecipeObject> matchedRecipeList = new ArrayList<>();

        // try to find the Recipe whose name is containing the recipe name from the argument.
        for (RecipeObject recipe : recipes)
        {
            if (recipe.getRecipeName().contains(recipeName))
            {
                matchedRecipeList.add(recipe);
            }
        }

        return matchedRecipeList;
    }

    /**
     * Responsibilities:
     *  - Search for a recipe having the target recipe ID
     *  - Remark: search based on recipe ID and should only be used by CategoryHandler
     **/
    public RecipeObject getRecipe(int recipeID)
    {
        RecipeObject matchedRecipe = null;

        // try to find the RecipeObject having the id
        // (assumption: id should be unique)
        for (RecipeObject recipe : recipes)
        {
            if (recipe.getRecipeId() == recipeID)
            {
                matchedRecipe = recipe;
                break;
            }
        }

        return matchedRecipe;
    }

    /**
     * Responsibilities:
     *  - validate category inside of the recipe object exists in the database
     *    (For iteration 2, we're having fixed categories, i.e. users CANNOT add new categories)
     *  - (?) parse instructions line-by-line (optional for now)
     *  - add recipe to DB
     *
     * PS: this requires categoryPersistence and ingredientPersistence
     **/
    public RecipeObject addRecipe(RecipeObject recipe)
    {
        // add recipe only when it passes these conditions
        if (!isRecipeNull(recipe) && !isRecipeIdUsedInDatabase(recipe))
        {
            // add to recipeDB
            recipes.add(recipe);

            // add to corresponding category's recipeList
            Category recipeCategory = recipe.getRecipeCategory();
            categoryPersistence.appendRecipeList(recipeCategory, recipe);

            // add new ingredients (responsibility of addIngredient(), checking if the ingredient exists or not).
            ArrayList<Ingredient> recipeIngredients = recipe.getRecipeIngredients();
            for (Ingredient ingredient : recipeIngredients)
            {
                ingredientPersistence.addIngredient(ingredient);
            }

            return recipe;
        }

        return null;
    }


    /**
     * deleteRecipe
     *
     * Responsibilities:
     *  - Delete a SINGLE recipe having the target recipe id.
     *  - Remark: search based on recipe id, which should be an UNIQUE attribute
     *
     * PS: should not delete corresponding ingredients
     * PS: should not delete the Category, but need to delete the one in the Category's recipeList (Later coz requires deleteRecipe(Category category, RecipeObject recipe) in Category)
     **/
    public void deleteRecipe(RecipeObject recipe)
    {
        if (!isRecipeNull(recipe))
        {
            // remove from recipes in Recipe DB
            recipes.remove(recipe);

            // remove from the recipeList in Category DB
            Category category = recipe.getRecipeCategory();
            if(category != null)
            {
                category.deleteRecipe(recipe);
            }

        }

    }

    /********************************************************
     * Helper Methods
     ********************************************************/

    private boolean isRecipeNull(RecipeObject recipe)
    {
        return recipe == null;
    }

    // If recipe id is used in the database already, a RecipeObject will be found from db. As a result, it will return true.
    private boolean isRecipeIdUsedInDatabase(RecipeObject recipe)
    {
        return getRecipe(recipe.getRecipeId()) != null;
    }

}
