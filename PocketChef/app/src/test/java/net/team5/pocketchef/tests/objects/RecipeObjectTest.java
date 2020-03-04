package net.team5.pocketchef.tests.objects;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RecipeObjectTest {

    /********************************************************
     * setup -- variables & methods
     ********************************************************/
    private String recipeName;
    private Category recipeCategory;
    private String recipeInstruction;
    private List<Ingredient> recipeIngredients;

    @Before
    public void setup() {
        this.recipeName = "Veggie Burgers";
        this.recipeCategory = new Category("Vegan");
        this.recipeInstruction = "1.XXXXX 2.XXXXX 3.XXXXXX";
        this.recipeIngredients = new ArrayList<>();
        this.recipeIngredients.add(new Ingredient("lettuce"));
        this.recipeIngredients.add(new Ingredient("tomatoes"));
    }

    /********************************************************
     * actual testing
     ********************************************************/
    @Test
    public void testCreatingRecipeObject()
    {

        System.out.println("\nStarting testCreatingRecipeObject");

        RecipeObject recipe = new RecipeObject(this.recipeName, this.recipeCategory, this.recipeInstruction, this.recipeIngredients);

        assertNotNull(recipe);
        assertTrue("0".equals(recipe.getRecipeId()));
        assertTrue("Vegan".equals(recipe.getRecipeCategory().getCategoryName()));
        assertTrue("1.XXXXX 2.XXXXX 3.XXXXXX".equals(recipe.getRecipeInstruction()));
        assertNotNull(recipe.getRecipeIngredients());
        assertTrue(recipe.getRecipeIngredients() instanceof List<?>);
        assertTrue("lettuce".equals(recipe.getRecipeIngredients().get(0).getIngredientName()));
        assertTrue("tomatoes".equals(recipe.getRecipeIngredients().get(1).getIngredientName()));

        System.out.println("Finished testCreatingRecipeObject");
    }
}
