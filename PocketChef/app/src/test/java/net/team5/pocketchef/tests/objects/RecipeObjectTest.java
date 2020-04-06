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
    private ArrayList<String> recipeInstructions;
    private ArrayList<Ingredient> recipeIngredients;


    @Before
    public void setup() {
        this.recipeName = "Veggie Burgers";
        this.recipeCategory = new Category("Vegan", new ArrayList<RecipeObject>());
        this.recipeInstructions = new ArrayList<>();
        this.recipeInstructions.add("1.XXXXX");
        this.recipeInstructions.add("2.XXXXX");
        this.recipeInstructions.add("3.XXXXX");
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

        RecipeObject recipe = new RecipeObject(this.recipeName, this.recipeCategory, this.recipeInstructions, this.recipeIngredients);

        // -- recipe & recipe id --
        assertNotNull(recipe);
        assertTrue(1 == recipe.getRecipeId());

        // -- recipe category --
        assertTrue("Vegan".equals(recipe.getRecipeCategory().getCategoryName()));

        // -- recipe instructions --
        assertNotNull(recipe.getRecipeInstructions());
        assertTrue(recipe.getRecipeInstructions() instanceof ArrayList<?>);
        assertTrue("1.XXXXX".equals(recipe.getRecipeInstructions().get(0)));
        assertTrue("2.XXXXX".equals(recipe.getRecipeInstructions().get(1)));
        assertTrue("3.XXXXX".equals(recipe.getRecipeInstructions().get(2)));

        // -- recipe ingredients --
        assertNotNull(recipe.getRecipeIngredients());
        assertTrue(recipe.getRecipeIngredients() instanceof ArrayList<?>);
        assertTrue("lettuce".equals(recipe.getRecipeIngredients().get(0).getIngredientName()));
        assertTrue("tomatoes".equals(recipe.getRecipeIngredients().get(1).getIngredientName()));

        System.out.println("\nFinished testCreatingRecipeObject");
    }
}
