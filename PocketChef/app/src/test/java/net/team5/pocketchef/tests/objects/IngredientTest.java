package net.team5.pocketchef.tests.objects;

import net.team5.pocketchef.Business.Objects.Ingredient;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void testCreatingIngredient()
    {

        System.out.println("\nStarting testCreatingIngredient");

        Ingredient ingredient = new Ingredient("eggs");

        assertNotNull(ingredient);
        assertTrue("eggs".equals(ingredient.getIngredientName()));

        System.out.println("Finished testCreatingIngredient");
    }

}
