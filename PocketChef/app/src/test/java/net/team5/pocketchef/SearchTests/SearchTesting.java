package net.team5.pocketchef.SearchTests;

import net.team5.pocketchef.Database.RecipeDatabase;
import net.team5.pocketchef.Business.Objects.Recipe.Ingredients;
import net.team5.pocketchef.Business.Objects.Recipe.Instructions;
import net.team5.pocketchef.Business.Objects.Recipe.Recipe;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchTesting{

    private Recipe makeRecipe(){
        ArrayList<String> ingreds = new ArrayList<String>();
        ingreds.add("3/4 cups patients");

        ArrayList<String> instruct = new ArrayList<String>();
        instruct.add("Pre-heat test oven");
        return new Recipe("TestCakes","Testing", new Ingredients(ingreds), new Instructions(instruct));
    }

    @Test
    public void addingToStorage(){
        RecipeDatabase database = new RecipeDatabase();
        database.addRecipe(makeRecipe());

        assertNotNull("The database is not null", database.getRecipes());

        assertNotNull("the recipe in the database is not null", database.getRecipes().get(0));

        assertNotNull("recipe name not null", database.getRecipes().get(0).recipeName);

        assertNotNull("category not null", database.getRecipes().get(0).category);

        assertNotNull("instruction pointer not null", database.getRecipes().get(0).getInstructList());

        assertNotNull("ingredient pointer  not null", database.getRecipes().get(0).getIngredList());

        assertNotNull("instruction list is not null", database.getRecipes().get(0).getInstructList().getInstructionList().get(0));

        assertNotNull("ingredient list is  not null", database.getRecipes().get(0).getIngredList().getIngredientList().get(0));

        assertTrue("Recipe that was added is the same as a recreation", database.getRecipes().get(0).sameRecipe(makeRecipe()));

        assertFalse("Recipe that was added should have a different id as the recreation", database.getRecipes().get(0).equals(makeRecipe()));
    }

    @Test
    public void cloning(){
        RecipeDatabase database = new RecipeDatabase();

        Recipe original = makeRecipe();
        Recipe clone = original.deepCopy();

        assertTrue("Clone should have the same ID", original.equals(clone));
        assertTrue("The recipe vars should be identical", original.sameRecipe(clone));

        original.recipeName = "testCakes 2.0";

        assertTrue("Clone should still have the same ID", original.equals(clone));
        assertFalse("The recipe vars should now be different", original.sameRecipe(clone));
    }

    /***
    check to make sure the deepCopy of ingredients works
    */
    @Test
    public void cloningIngredients(){

        Recipe original = makeRecipe();
        Recipe clone = original.deepCopy();

        assertTrue("Clone should have the same ID", original.equals(clone));
        assertTrue("The recipe vars should be identical", original.sameRecipe(clone));

        Ingredients oriIngred = original.getIngredList();

        assertTrue("oriIngred should be equal right now", oriIngred.equals(clone.getIngredList()));

        //get the list and add something
        ArrayList<String> list = oriIngred.getIngredientList();
        list.add("New test case");

        assertFalse("The ingredient list should now be different", oriIngred.equals(clone.getIngredList().getIngredientList()));
    }

    /***
     check to make sure the deepCopy of ingredients works
     */
    @Test
    public void cloningInstructions(){

        Recipe original = makeRecipe();
        Recipe clone = original.deepCopy();

        assertTrue("Clone should have the same ID", original.equals(clone));
        assertTrue("The recipe vars should be identical", original.sameRecipe(clone));

        Instructions oriInstruct = original.getInstructList();

        assertTrue("oriInstruct should be equal right now", oriInstruct.equals(clone.getInstructList()));

        //get the list and add something
        ArrayList<String> list = oriInstruct.getInstructionList();
        list.add("New test case");

        assertFalse("The instruction list should now be different", oriInstruct.equals(clone.getInstructList().getInstructionList()));
    }
}
