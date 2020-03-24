package net.team5.pocketchef.tests.persistences;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.Recipe.Instructions;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.Database.IngredientPersistence;
import net.team5.pocketchef.Database.RecipePersistence;
import net.team5.pocketchef.Database.stubs.CategoryPersistenceStub;
import net.team5.pocketchef.Database.stubs.IngredientPersistenceStub;
import net.team5.pocketchef.Database.stubs.RecipePersistenceStub;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RecipePersistenceTest {
    private RecipePersistence recipePersistence;
    private CategoryPersistence categoryPersistence;
    private IngredientPersistence ingredientPersistence;

    @Before
    public void setup()
    {
        System.out.println("Starting tests for RecipePersistenceTest...");

//        // initialize initialRecipeId
//        // (assumption: RecipeObject's counter begins from zero -> can be dangerous.)
//        this.initialRecipeId = 0;

        // initialize persistences
        this.categoryPersistence = new CategoryPersistenceStub();
        this.ingredientPersistence = new IngredientPersistenceStub();
        this.recipePersistence = new RecipePersistenceStub(this.categoryPersistence, this.ingredientPersistence);
    }

    @Test
    public void testGetRecipes()
    {
        ArrayList<RecipeObject> recipes = this.recipePersistence.getRecipes();
        assertNotNull(recipes);
        assertEquals(3, recipes.size());
    }

    @Test
    public void testGetRecipesWithSingleRecipeHavingTheSameRecipeName()
    {
        ArrayList<RecipeObject> recipes = this.recipePersistence.getRecipes("Fried Eggs");
        assertNotNull(recipes);
        assertEquals(1, recipes.size());
    }

    @Test
    public void testGetRecipesWithMultipleRecipesHavingTheSameRecipeName()
    {
        ArrayList<RecipeObject> recipes = this.recipePersistence.getRecipes("(Faked)");
        assertNotNull(recipes);
        assertEquals(3, recipes.size());
    }

    @Test
    public void testGetRecipesWithNoRecipeHavingTheSameRecipeName()
    {
        ArrayList<RecipeObject> recipes = this.recipePersistence.getRecipes("NoRecipeShouldHaveThisName");
        assertNotNull(recipes);
        assertEquals(0, recipes.size());
    }

    @Test
    public void testGetRecipe()
    {
        // pre-test checking
        assertEquals(this.recipePersistence.getRecipes("(Faked) Fried Eggs").size(), 1);
        assertEquals(this.recipePersistence.getRecipes("(Faked) Mexican Breakfast").size(), 1);
        assertEquals(this.recipePersistence.getRecipes("(Faked) Mexican Bread").size(), 1);

        // retrieve id
        int friedEggsRecipeId = this.recipePersistence.getRecipes("(Faked) Fried Eggs").get(0).getRecipeId();
        int mexicanBreakfastRecipeId = this.recipePersistence.getRecipes("(Faked) Mexican Breakfast").get(0).getRecipeId();
        int mexicanBreadRecipeId = this.recipePersistence.getRecipes("(Faked) Mexican Bread").get(0).getRecipeId();

        // test using the id
        assertEquals("(Faked) Fried Eggs", this.recipePersistence.getRecipe(friedEggsRecipeId).getRecipeName());
        assertEquals("(Faked) Mexican Breakfast", this.recipePersistence.getRecipe(mexicanBreakfastRecipeId).getRecipeName());
        assertEquals("(Faked) Mexican Bread", this.recipePersistence.getRecipe(mexicanBreadRecipeId).getRecipeName());
    }

    /**
     * Responsibilities:
     *  - validate category inside of the recipe object exists in the database
     *    (For iteration 2, we're having fixed categories, i.e. users CANNOT add new categories)
     *  - (?) parse instructions line-by-line (optional for now)
     *  - add recipe to DB
     **/
    @Test
    public void testAddRecipeWithNewRecipe()
    {
        // save number of recipes before adding for later testing
        int prevNumRecipes = this.recipePersistence.getRecipes().size();
        int prevNumRecipesInVeganCategory = this.categoryPersistence.getCategory("Vegan").getRecipeList().size();
        int prevNumIngredientsInDB = this.ingredientPersistence.getIngredients().size();

        // declare variables for creating a new recipe
        String recipeName;
        Category recipeCategory;
        ArrayList<String> singleRecipeInstructionList;
        ArrayList<Ingredient> singleRecipeIngredientList;

        // initialize variables for creating a new recipe
        recipeName = "(Test) Veggie Burgers";
        recipeCategory = this.categoryPersistence.getCategory("Vegan");
        singleRecipeInstructionList = new ArrayList<>();
        singleRecipeInstructionList.add("1.XXXXX");
        singleRecipeInstructionList.add("2.XXXXX");
        singleRecipeInstructionList.add("3.XXXXX");
        singleRecipeIngredientList = new ArrayList<>();
        singleRecipeIngredientList.add(new Ingredient("lettuce"));
        singleRecipeIngredientList.add(new Ingredient("tomatoes"));

        // create a new recipe
        RecipeObject newRecipe = new RecipeObject(recipeName, recipeCategory, singleRecipeInstructionList, singleRecipeIngredientList);

        // add to the database
        this.recipePersistence.addRecipe(newRecipe);

        // These variables are for testing and for increasing readability
        RecipeObject newRecipeFromDB = this.recipePersistence.getRecipes("(Test) Veggie Burgers").get(0);
        Category newRecipeCategoryFromDB = newRecipeFromDB.getRecipeCategory();
        ArrayList<String> newRecipeInstructionsFromDB = newRecipeFromDB.getRecipeInstructions();
        ArrayList<Ingredient> newRecipeIngredientFromDB = newRecipeFromDB.getRecipeIngredients();

        // do testing for recipeDB
        assertEquals(prevNumRecipes+1, this.recipePersistence.getRecipes().size());
        assertEquals("Vegan", newRecipeCategoryFromDB.getCategoryName());
        assertEquals(3, newRecipeInstructionsFromDB.size());
        assertEquals("1.XXXXX", newRecipeInstructionsFromDB.get(0));
        assertEquals(2, newRecipeIngredientFromDB.size());
        assertEquals("lettuce", newRecipeIngredientFromDB.get(0).getIngredientName());

        // do testing for categoryDB
        // (may need to access arrayList and check if the object exists)
        assertEquals(prevNumRecipesInVeganCategory+1, this.categoryPersistence.getCategory("Vegan").getRecipeList().size());

        // do testing for ingredientDB
        // (may need to access arrayList and check if the object exists)
        assertEquals(prevNumIngredientsInDB+2, this.ingredientPersistence.getIngredients().size());
    }

    @Test
    public void testAddRecipeWithRecipeExistingInDB()
    {
        // save number of recipes before adding for later testing
        int prevNumRecipes = this.recipePersistence.getRecipes().size();
        int prevNumRecipesInVeganCategory = this.categoryPersistence.getCategory("Vegan").getRecipeList().size();
        int prevNumIngredientsInDB = this.ingredientPersistence.getIngredients().size();

        // retrieve a recipe from db for adding.
        // (assumption: "(Faked) Fried Eggs" recipe exists, where it is a Vegan dish, and it has 4 instructions and 3 ingredients)
        RecipeObject newRecipe = this.recipePersistence.getRecipes("(Faked) Fried Eggs").get(0);

        // add this to the database
        this.recipePersistence.addRecipe(newRecipe);

        // These variables are for testing and for increasing readability
        Category newRecipeCategory = newRecipe.getRecipeCategory();
        ArrayList<String> newRecipeInstructions = newRecipe.getRecipeInstructions();
        ArrayList<Ingredient> newRecipeIngredient = newRecipe.getRecipeIngredients();

        // do testing for recipeDB, categoryDB, and ingredientDB (should be unchanged)
        assertEquals(prevNumRecipes, this.recipePersistence.getRecipes().size());
        assertEquals(prevNumRecipesInVeganCategory, this.categoryPersistence.getCategory("Vegan").getRecipeList().size());
        assertEquals(prevNumIngredientsInDB, this.ingredientPersistence.getIngredients().size());
    }

    /**
     * deleteRecipe
     *
     * Responsibilities:
     *  - Delete a SINGLE recipe having the target recipe id.
     *  - Remark: search based on recipe id, which should be an UNIQUE attribute
     **/
    @Test
    public void testDeleteRecipeWithNonExistingRecipe()
    {
        int prevNumRecipes = this.recipePersistence.getRecipes().size();
        int prevNumRecipesInVeganCategory = this.categoryPersistence.getCategory("Vegan").getRecipeList().size();
        int prevNumIngredientsInDB = this.ingredientPersistence.getIngredients().size();

        // declare variables for creating a new recipe
        String recipeName;
        Category recipeCategory;
        ArrayList<String> singleRecipeInstructionList;
        ArrayList<Ingredient> singleRecipeIngredientList;

        // initialize variables for creating a new recipe
        recipeName = "(Test) Veggie Burgers";
        recipeCategory = this.categoryPersistence.getCategory("Vegan");
        singleRecipeInstructionList = new ArrayList<>();
        singleRecipeInstructionList.add("1.XXXXX");
        singleRecipeInstructionList.add("2.XXXXX");
        singleRecipeInstructionList.add("3.XXXXX");
        singleRecipeIngredientList = new ArrayList<>();
        singleRecipeIngredientList.add(new Ingredient("lettuce"));
        singleRecipeIngredientList.add(new Ingredient("tomatoes"));

        // create a new recipe
        RecipeObject newRecipe = new RecipeObject(recipeName, recipeCategory, singleRecipeInstructionList, singleRecipeIngredientList);

        // delete this recipe
        this.recipePersistence.deleteRecipe(newRecipe);

        // do testing for recipeDB, categoryDB, and ingredientDB (should be unchanged)
        assertEquals(prevNumRecipes, this.recipePersistence.getRecipes().size());
        assertEquals(prevNumRecipesInVeganCategory, this.categoryPersistence.getCategory("Vegan").getRecipeList().size());
        assertEquals(prevNumIngredientsInDB, this.ingredientPersistence.getIngredients().size());
    }

    @Test
    public void testDeleteRecipeWithExistingRecipe()
    {
        int prevNumRecipes = this.recipePersistence.getRecipes().size();
        int prevNumRecipesInVeganCategory = this.categoryPersistence.getCategory("Vegan").getRecipeList().size();
        int prevNumIngredientsInDB = this.ingredientPersistence.getIngredients().size();

        // retrieve a recipe from db for deletion.
        // (assumption: "(Faked) Fried Eggs" recipe exists, where it is a Vegan dish, and it has 4 instructions and 3 ingredients)
        RecipeObject newRecipe = this.recipePersistence.getRecipes("(Faked) Fried Eggs").get(0);

        // delete this recipe
        this.recipePersistence.deleteRecipe(newRecipe);

        // do testing for recipeDB, categoryDB, and ingredientDB (only ingredient list should be unchanged)
        assertEquals(prevNumRecipes-1, this.recipePersistence.getRecipes().size());
        assertEquals(prevNumRecipesInVeganCategory-1, this.categoryPersistence.getCategory("Vegan").getRecipeList().size());
        assertEquals(prevNumIngredientsInDB, this.ingredientPersistence.getIngredients().size());
    }

    @After
    public void clean() {
        System.out.println("Finished tests for RecipePersistenceTest...");
    }
}
