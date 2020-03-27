package net.team5.pocketchef.tests.business;

import net.team5.pocketchef.Business.DBLogic.DBManager;
import net.team5.pocketchef.Business.Objects.Ingredient;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * IngredientPersistenceTest:
 *
 * This is an integration test that tests DBManager on its Ingrdient methods:
 *
 * addIngredient()
 * -tests that an ingredient can be added
 *
 * getIngredient()
 * -tests that an existing ingredient can be found
 * -tests that a non existing ingredient returns null
 *
 * deleteIngredient()
 * -tests that an existing ingredient can be deleted
 *
 * By Beni
 **/

public class DBManagerIngredientPersistenceTest {

    private DBManager manager;
    private Ingredient ingredient;

    @Before
    public void setup()
    {
        //TODO: attach fake DB to DBManager
        /** Create DBManager **/
        manager = new DBManager();
        manager.setUp();

        /** Create Ingredient **/
        ingredient = new Ingredient("TestIngred");
    }

    @Test
    public void ingredientPersistence()
    {
        try
        {
            /** Add an ingredient to the DB **/
            ingredient = manager.addIngredient(ingredient);

            /** Test addRecipe **/
            assertNotNull(ingredient);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in ingredientPersistence() test");

            fail();
        }

        System.out.println("Finished testing addIngredient()");

        getIngredient();
    }

    public void getIngredient()
    {
        try
        {
            /** Test getIngredient on added Ingredient **/
            ingredient = manager.getIngredient(ingredient.getIngredientName());

            /** Ensure ingredient was added **/
            assertNotNull(ingredient);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in checkDeletedRecipe() test");

            fail();

        }

        System.out.println("Finished testing getIngredient() on existing Ingredient");

        deleteIngredient();
    }

    public void deleteIngredient()
    {
        try
        {
            /** Test deleteIngredient on added Ingredient **/
             manager.deleteIngredient(ingredient);

            /** Ensure ingredient was added **/
            assertNull(manager.getIngredient(ingredient.getIngredientName()));
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in deleteIngredient() test");

            fail();

        }

        System.out.println("Finished testing deleteIngredient() on existing Ingredient");
    }

    @Test
    public void getNullIngredient()
    {
        try
        {
            /** Test getIngredient on non existing Ingredient **/
            ingredient = manager.getIngredient("NonExistingIngred");

            /** Ensure the non existing ingredient was not found and returned null **/
            assertNull(ingredient);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in getNullIngredient() test");

            fail();

        }

        System.out.println("Finished testing getIngredient() on non existing ingredient");
    }

}

