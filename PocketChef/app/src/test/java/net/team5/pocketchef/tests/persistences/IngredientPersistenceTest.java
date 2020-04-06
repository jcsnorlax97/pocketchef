package net.team5.pocketchef.tests.persistences;

import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Database.IngredientPersistence;
import net.team5.pocketchef.Database.stubs.IngredientPersistenceStub;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * IngredientPersistenceTest:
 *
 * This is an integration test that tests for IngredientPersistenceStub.java:
 *
 * getIngredients()
 * - testGetIngredients()
 *
 * getIngredient()
 * - testGetIngredientWithSameNameAndSameCases() -> (an existing ingredient that can be found)
 * - testGetIngredientWithSameNameAndDifferentCases() -> (an existing ingredient that can be found)
 * - testGetIngredientWithDifferentName() -> (a NON existing ingredient that CANNOT be found) -> null is returned
 * - testGetIngredientWithTrailingSpaces() -> (an existing ingredient that can be found)
 *
 * addIngredient()
 * - testAddIngredientWithExistingIngredients() -> Exist -> do nothing
 * - testAddIngredientWithNonExistingIngredients() -> NON Exist -> insert this to db (no letter case conversion, only trimming)
 *
 * PS: may format ingredient name before adding it into DB by creating new Ingredient with the formatted name.
 **/
public class IngredientPersistenceTest {
    private IngredientPersistence ingredientPersistence;

    @Before
    public void setup()
    {
        System.out.println("Starting tests for IngredientPersistenceTest...");
        this.ingredientPersistence = new IngredientPersistenceStub();
    }

    @Test
    public void testGetIngredients() {
        ArrayList<Ingredient> ingredients = this.ingredientPersistence.getIngredients();
        assertNotNull(ingredients);
        assertEquals(6, ingredients.size());
    }

    @Test
    public void testGetIngredientWithSameNameAndSameCases() {
        assertEquals("Eggs", this.ingredientPersistence.getIngredient("Eggs").getIngredientName()); // can be found
        assertEquals("Bacons", this.ingredientPersistence.getIngredient("Bacons").getIngredientName()); // can be found
        assertEquals("1% skim milk", this.ingredientPersistence.getIngredient("1% skim milk").getIngredientName()); // can be found
    }

    @Test
    public void testGetIngredientWithSameNameAndDifferentCases() {
        assertEquals("Eggs", this.ingredientPersistence.getIngredient("eggs").getIngredientName()); // can be found
        assertEquals("Bacons", this.ingredientPersistence.getIngredient("bacons").getIngredientName()); // can be found
        assertEquals("1% skim milk", this.ingredientPersistence.getIngredient("1% SKIM MILK").getIngredientName()); // can be found
    }

    @Test
    public void testGetIngredientWithDifferentName() {
        assertNull("Eggs", this.ingredientPersistence.getIngredient("egges")); // CANNOT be found
        assertNull("Bacons", this.ingredientPersistence.getIngredient("Two bacons")); // CANNOT be found
        assertNull("1% skim milk", this.ingredientPersistence.getIngredient("1%skim milk")); // CANNOT be found
    }

    @Test
    public void testGetIngredientWithTrailingSpaces() {
        assertEquals("Eggs", this.ingredientPersistence.getIngredient("        eggs").getIngredientName()); // can be found
        assertEquals("Bacons", this.ingredientPersistence.getIngredient("bacons        ").getIngredientName()); // can be found
        assertEquals("1% skim milk", this.ingredientPersistence.getIngredient("    1% skim milk       ").getIngredientName()); // can be found
    }

    @Test
    public void testAddIngredientWithExistingIngredients() {
        // initialize size variable for later comparison.
        int previousIngredientListSize = this.ingredientPersistence.getIngredients().size();

        // create ingredients & add them to faked database
        Ingredient eggsIngredient = new Ingredient("Eggs"); // Exist already (Same letter case -> same name).
        Ingredient baconsIngredient = new Ingredient("bacons"); // Exist already (Different letter case -> still same name).
        this.ingredientPersistence.addIngredient(eggsIngredient);
        this.ingredientPersistence.addIngredient(baconsIngredient);

        // do testings
        assertEquals(previousIngredientListSize, this.ingredientPersistence.getIngredients().size());
        assertEquals("Eggs", this.ingredientPersistence.getIngredient("Eggs").getIngredientName());
        assertEquals("Bacons", this.ingredientPersistence.getIngredient("bacons").getIngredientName());
    }

    @Test
    public void testAddIngredientWithNonExistingIngredients() {
        // initialize size variable for later comparison.
        int previousIngredientListSize = this.ingredientPersistence.getIngredients().size();

        // create ingredients & add them to faked database
        Ingredient hamsIngredient = new Ingredient("Hams"); // Not exist yet.
        Ingredient twoPercentSkimMilkIngredient = new Ingredient("2% SKIM MILK"); // Not exist yet.
        this.ingredientPersistence.addIngredient(hamsIngredient);
        this.ingredientPersistence.addIngredient(twoPercentSkimMilkIngredient);

        // do testings
        assertEquals(previousIngredientListSize+2, this.ingredientPersistence.getIngredients().size());
        assertEquals("Hams", this.ingredientPersistence.getIngredient("Hams").getIngredientName());
        assertEquals("2% SKIM MILK", this.ingredientPersistence.getIngredient("2% SKIM MILK").getIngredientName());
    }

    @After
    public void clean() {
        System.out.println("Finished tests for IngredientPersistenceTest...");
    }
}