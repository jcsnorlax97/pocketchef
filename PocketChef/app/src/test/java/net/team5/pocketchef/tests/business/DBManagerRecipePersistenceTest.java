package net.team5.pocketchef.tests.business;

import net.team5.pocketchef.Business.DBLogic.DBManager;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

/**
 * RecipePersistenceTest:
 *
 * This is an integration test that tests DBManager on its Recipe methods:
 *
 * addRecipe()
 * -tests that a recipe can be added
 *
 * deleteRecipe()
 * -tests that a recipe can be deleted
 *
 * getRecipe()
 * -tests that an existing recipe can be found
 * -tests that a non existing recipe returns null
 **/

public class DBManagerRecipePersistenceTest
{

    private DBManager manager;
    private RecipeObject recipe;
    private ArrayList<String> instructions;
    private ArrayList<Ingredient> ingredients;


    @Before
    public void setup()
    {
        //TODO: attach fake DB to DBManager
        /** Create DBManager **/
        manager = new DBManager();
        manager.setUp();

        /** Create instructions **/
        instructions = new ArrayList<>();
        instructions.add("Test Instructions 1");
        instructions.add("Test Instructions 2");

        /** Create ingredients **/
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("testIngredient"));
        ingredients.add(new Ingredient("testIngredient2"));

        /** Create Recipe
         * Set ID to -1 as to not overwrite any possible items in the DB
         **/
        recipe = new RecipeObject(-1, "TestName", null, instructions, ingredients);
    }

    @Test
    public void recipePersistence()
    {
        try
        {
            /** Add a recipe to the DB **/
            recipe = manager.addRecipe(recipe);

            /** Test addRecipe **/
            assertNotNull(recipe);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in recipePersistence() test");

            fail();
        }

        System.out.println("Finished testing addRecipe()");

        checkRecipe();
    }

    public void checkRecipe()
    {
        try
        {
            /** Test getRecipe on an existing recipe **/
            recipe = manager.getRecipe(recipe.getRecipeId());

            /** Ensure recipe was added **/
            assertNotNull(recipe);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in checkRecipe() test");

            fail();
        }

        /** Test Delete **/
        deleteRecipe();
    }

    /** This cleans up the test itself so no @After is required **/
    public void deleteRecipe()
    {
        try
        {
            /** Delete a recipe from the DB **/
            manager.deleteRecipe(recipe);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in deleteRecipe() test");

            fail();
        }

        /** Attempt to get deleted recipe to ensure it deleted **/
        checkDeletedRecipe();
    }

    public void checkDeletedRecipe()
    {
        try
        {
            /** Test getRecipe on a non existing recipe **/
            recipe = manager.getRecipe(recipe.getRecipeId());

            /** Ensure recipe was deleted **/
            assertNull(recipe);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in checkDeletedRecipe() test");

            fail();
        }

        System.out.println("Finished testing deleteRecipe() and getRecipe()");
    }
}
