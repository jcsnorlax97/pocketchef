package net.team5.pocketchef.tests.objects;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CategoryTest
 * This class tests for Category object creation.
 *
 * TODO:
 * - test for appendRecipe() as well.
 */
public class CategoryTest {

    private String veganCategoryName;
    private Category veganCategory;

    @Before
    public void setup()
    {
        this.veganCategoryName = "Vegan";
        this.veganCategory = new Category(veganCategoryName, new ArrayList<RecipeObject>());
    }

    /**
     * to create two categories
     */
    @Test
    public void testCreatingCategories()
    {

        System.out.println("\nStarting testCreatingCategories");

        assertNotNull(this.veganCategory);
        assertTrue(this.veganCategoryName.equals(this.veganCategory.getCategoryName()));
        assertNotNull(this.veganCategory.getRecipeList());
        assertEquals(0, this.veganCategory.getRecipeList().size());
        assertTrue(this.veganCategory.getRecipeList() instanceof ArrayList<?>);

        System.out.println("Finished testCreatingCategories");
    }

}
