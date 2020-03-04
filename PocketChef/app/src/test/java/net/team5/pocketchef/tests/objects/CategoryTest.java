package net.team5.pocketchef.tests.objects;

import net.team5.pocketchef.Business.Objects.Category;

import org.junit.Before;
import org.junit.Test;

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
    private String asianCategoryName;

    @Before
    public void setup() {
        this.veganCategoryName = "Vegan";
        this.asianCategoryName = "Asian";
    }

    /**
     * to create two categories
     */
    @Test
    public void testCreatingCategories()
    {

        System.out.println("\nStarting testCreatingCategories");

        Category veganCategory = new Category(veganCategoryName);
        Category asianCategory = new Category(asianCategoryName);

        assertNotNull(veganCategory);
        assertNotNull(asianCategory);
        assertTrue(this.veganCategoryName.equals(veganCategory.getCategoryName()));
        assertTrue(this.asianCategoryName.equals(asianCategory.getCategoryName()));
        assertTrue("0".equals(veganCategory.getCategoryId()));
        assertTrue("1".equals(asianCategory.getCategoryId()));
        assertNotNull(veganCategory.getRecipeList());
        assertNotNull(asianCategory.getRecipeList());
        assertTrue(veganCategory.getRecipeList() instanceof List<?>);
        assertTrue(asianCategory.getRecipeList() instanceof List<?>);

        System.out.println("Finished testCreatingCategories");
    }

}
