package net.team5.pocketchef.tests.persistences;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.Database.stubs.CategoryPersistenceStub;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * CategoryPersistenceTest:
 *
 * This is an integration test that tests for CategoryPersistenceStub.java:
 */

public class CategoryPersistenceTest
{
    private CategoryPersistence categoryPersistence;


    @Before
    public void setup()
    {
        System.out.println("Starting tests for CategoryPersistence...");
        categoryPersistence = new CategoryPersistenceStub();
    }

    @Test
    public void testGetCategories() {
        ArrayList<Category> categories = categoryPersistence.getCategories();
        assertEquals(3, categories.size());
    }

    @Test
    public void testGetCategory() {
        assertNotNull(categoryPersistence.getCategory("Vegan"));
        assertNull(categoryPersistence.getCategory("NonExistingCategory"));
        assertEquals("Vegan", categoryPersistence.getCategory("Vegan").getCategoryName());
    }

    @Test
    public void testCreateCategory() {
        Category newCategory = new Category("Canadian", null);
        categoryPersistence.createCategory(newCategory);
        assertEquals(4, categoryPersistence.getCategories().size());
        assertEquals("Canadian", categoryPersistence.getCategory("Canadian").getCategoryName());
    }

    @Test
    public void testAppendRecipeListValidCase() {

        // initialize variables required for (i) retrieving an existing Category and (ii) creating a new RecipeObject.
        String categoryName = "Vegan"; // (i)
        String newRecipeName = "Veggie Burgers"; // (ii)
        ArrayList<String> newRecipeInstructions = new ArrayList<>(); // (ii)
        newRecipeInstructions.add("1.XXXXX"); // (ii)
        newRecipeInstructions.add("2.XXXXX"); // (ii)
        newRecipeInstructions.add("3.XXXXX"); // (ii)
        ArrayList<Ingredient> newRecipeIngredients = new ArrayList<>(); // (ii)
        newRecipeIngredients.add(new Ingredient("lettuce")); // (ii)
        newRecipeIngredients.add(new Ingredient("tomatoes")); // (ii)

        // save current category list information for later uses and comparison.
        int prevCategoryRecipeListSize = categoryPersistence.getCategory(categoryName).getRecipeList().size();

        // retrieve target existing category
        Category veganCategory = categoryPersistence.getCategory(categoryName);

        // create new recipe object
        RecipeObject newRecipeObject = new RecipeObject(newRecipeName, veganCategory, newRecipeInstructions, newRecipeIngredients);

        // append new recipe into the target category
        assertNotNull(categoryPersistence.appendRecipeList(veganCategory, newRecipeObject));

        // do assertion tests
        assertEquals(prevCategoryRecipeListSize+1, categoryPersistence.getCategory(categoryName).getRecipeList().size());
        assertEquals(newRecipeName, categoryPersistence.getCategory(categoryName).getRecipeList().get(0).getRecipeName());
        assertEquals(categoryName, categoryPersistence.getCategory(categoryName).getRecipeList().get(0).getRecipeCategory().getCategoryName());
        assertEquals(3, categoryPersistence.getCategory(categoryName).getRecipeList().get(0).getRecipeInstructions().size());
        assertEquals(2, categoryPersistence.getCategory(categoryName).getRecipeList().get(0).getRecipeIngredients().size());
    }

    @Test
    public void testAppendRecipeListWithNullCategoryCase() {

        // initialize variables required for (i) retrieving an existing Category and (ii) creating a new RecipeObject.
        String categoryName = "Comp4190"; // (i)
        String newRecipeName = "Veggie Burgers"; // (ii)
        ArrayList<String> newRecipeInstructions = new ArrayList<>(); // (ii)
        newRecipeInstructions.add("1.XXXXX"); // (ii)
        newRecipeInstructions.add("2.XXXXX"); // (ii)
        newRecipeInstructions.add("3.XXXXX"); // (ii)
        ArrayList<Ingredient> newRecipeIngredients = new ArrayList<>(); // (ii)
        newRecipeIngredients.add(new Ingredient("lettuce")); // (ii)
        newRecipeIngredients.add(new Ingredient("tomatoes")); // (ii)

        // retrieve target existing category
        Category invalidCategory = categoryPersistence.getCategory(categoryName);
        assertNull(invalidCategory);

        // create new recipe object with invalid category object
        RecipeObject newRecipeObject = new RecipeObject(newRecipeName, invalidCategory, newRecipeInstructions, newRecipeIngredients);

        // append new recipe into the target category
        assertNull(categoryPersistence.appendRecipeList(invalidCategory, newRecipeObject));
    }

    @After
    public void clean() {
        System.out.println("Finished tests for CategoryPersistence...");
    }

}