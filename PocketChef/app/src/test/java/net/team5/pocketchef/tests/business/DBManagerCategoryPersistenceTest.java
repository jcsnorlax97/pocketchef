package net.team5.pocketchef.tests.business;

import net.team5.pocketchef.Business.DBLogic.DBManager;
import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * CategoryPersistenceTest:
 *
 * This is an integration test that tests DBManager on its Category methods:
 *
 * createCategory()
 * -tests that a Category can be added
 *
 * getCategory()
 * -tests that an existing Category can be found
 *
 * getNullCategory()
 * -tests that a non existing Category returns null
 *
 * appendCategory()
 * -tests that a recipe can be added to an existing Category
 *
 * getNullAppend()
 * -tests that a recipe can not be added to a non existing Category
 *
 * deleteRecipe()
 * -tests that a recipe can be deleted from an existing Category
 *
 * getNullDelete()
 * -tests that a recipe can not be deleted from a non existing category
 *
 * deleteCategory()
 * -Tests that an existing Category can be deleted
 *
 * By Beni
 **/

public class DBManagerCategoryPersistenceTest
{
    private DBManager manager;
    private Category category;
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

        /** Create Category **/
        category = new Category("TestCategory", null);

        /** Create instructions **/
        instructions = new ArrayList<>();
        instructions.add("Test Instructions 1");
        instructions.add("Test Instructions 2");

        /** Create ingredients **/
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("testIngredient"));
        ingredients.add(new Ingredient("testIngredient2"));

        /** Create Recipe **/
        recipe = new RecipeObject(-1, "TestName", null, instructions, ingredients);
    }

    @Test
    public void categoryPersistence()
    {
        try
        {
            /** Add a Category to the DB **/
            category = manager.createCategory(category);

            /** Test createCategory **/
            assertNotNull(category);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in categoryPersistence() test");

            fail();
        }

        System.out.println("Finished testing categoryPersistence()");

        getCategory();
    }

    public void getCategory()
    {
        try
        {
            /** Test getCategory on added Category **/
            category = manager.getCategory(category.getCategoryName());

            /** Ensure category was added **/
            assertNotNull(category);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in getCategory() test");

            fail();

        }

        System.out.println("Finished testing getCategory() on existing Category");

        appendCategory();
    }

    public void appendCategory()
    {
        try
        {
            /** Test appendRecipeList by adding recipe **/
            category = manager.appendRecipeList(category, recipe);

            /** Ensure recipe was added **/
            assertNotNull(category.getRecipeList().get(0));
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in checkDeletedRecipe() test");

            fail();

        }

        System.out.println("Finished testing appendCategory() on existing Category");

        deleteRecipe();
    }

    public void deleteRecipe()
    {
        try
        {
            /** Test deleteRecipe by deleting recipe **/
            category = manager.deleteRecipe(category, recipe);

            /** Ensure recipe was deleted **/
            assertEquals(category.getRecipeList().size(), 0);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in deleteRecipe() test");

            fail();

        }

        System.out.println("Finished testing deleteRecipe() on existing Category");

        deleteCategory();
    }

    public void deleteCategory()
    {
        try
        {
            /** Test deleteCategory by deleting category **/
            manager.deleteCategory(category);

            /** Ensure recipe was deleted **/
            assertNull(manager.getCategory(category.getCategoryName()));
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in checkDeletedRecipe() test");

            fail();

        }

        System.out.println("Finished testing deleteCategory() on existing Category");
    }

    @Test
    public void getNullCategory()
    {
        try
        {
            /** Test getCategory on non existing Category **/
            category = manager.getCategory("NonExistingCategory");

            /** Ensure the non existing Category was not found and returned null **/
            assertNull(category);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in getCategory() test");

            fail();

        }

        System.out.println("Finished testing getCategory() on non existing Category");
    }

    @Test
    public void getNullAppend()
    {
        try
        {
            /** Test appendRecipeList by adding recipe to null category **/
            category = manager.appendRecipeList(null, recipe);

            /** Ensure the non existing Category was not found and returned null **/
            assertNull(category);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in getNullAppend() test");

            fail();

        }

        System.out.println("Finished testing appendRecipeList() on non existing Category");
    }

    @Test
    public void getNullDelete()
    {
        try
        {
            /** Test deleteCategory by deleting null **/
            manager.deleteCategory(null);

            /** If exception did not happen, it was handled correctly **/
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in getNullDelete() test");

            fail();

        }

        System.out.println("Finished testing deleteCategory() on non existing Category");
    }
}
