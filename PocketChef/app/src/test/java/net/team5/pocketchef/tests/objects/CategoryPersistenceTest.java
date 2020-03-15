package net.team5.pocketchef.tests.objects;

import net.team5.pocketchef.Business.DBLogic.DBManager;
import net.team5.pocketchef.Business.Objects.Category;

import org.junit.Before;
import org.junit.Test;

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
 * -tests that a non existing Category returns null
 **/

public class CategoryPersistenceTest
{
    private DBManager manager;
    private Category category;

    @Before
    public void setup()
    {
        //TODO: attach fake DB to DBManager
        /** Create DBManager **/
        manager = new DBManager();
        manager.setUp();

        /** Create Category **/
        category = new Category("TestCategory", null);
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

        System.out.println("Finished testing addIngredient()");

        getCategory();
    }

    public void getCategory()
    {
        try
        {
            /** Test getCategory on added Category **/
            category = manager.getCategory(category.getCategoryName());

            /** Ensure ingredient was added **/
            assertNotNull(category);
        } catch (Exception e)
        {
            /** For Dev to check **/
            e.printStackTrace();
            System.out.println("Unexpected Error Occurred in checkDeletedRecipe() test");

            fail();

        }

        System.out.println("Finished testing getCategory() on existing Category");
    }

    @Test
    public void getNullIngredient()
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
}
