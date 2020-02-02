package net.team5.pocketchef.objects.Recipe;

import java.util.ArrayList;

/**
 *
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 1
 * John Hiebert
 * Feb 1 2020
 *
 * The class for storing whole recipes
 *
 * Contains:
 * --Variables--
 * Static int id: the id used to id each recipe (recipe 1 is id 0, recipe 2 is id 1 and so on)
 *
 * int recipeID: the id of the current recipe
 * String RecipeName: the name of the recipe
 * String Category: The category the recipe falls under (Mexican, Itilian ect)
 * Ingredients ingredList: The list of ingredients needed to make the recipe
 * Instructions instrucList: the list of instructions for how to make the recipe
 *
 * --Methods--
 * getInstructList: returns the pointer to the InstructList
 * getIngredList: returns the pointer to the IngredList
 */
public class Recipe {

    private static int id;

    private int recipeID;//the unique ID of the recipe

    public String recipeName;
    public String category;

    private Ingredients ingredList;
    private Instructions instructList;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param name the name of the recipe
     * @param categoryType the category the recipe falls under
     * @param ingredientList pointer to the Ingredients variable of the list of ingredients for this recipe
     * @param instructionList the pointer to the Instruction variable of the list of instructions for thie recipe
     * */
    public Recipe(String name, String categoryType, Ingredients ingredientList, Instructions instructionList){
        recipeName = name;
        category = categoryType;

        ingredList = ingredientList;
        instructList = instructionList;

        recipeID = id++;
    }

    /**
     * @param name the name of the recipe
     * @param categoryType the category the recipe falls under
     * @param ingredientList a string[] of the ingredients of the recipe, gets copied in Ingredients
     * @param instructionList a string[] of the instructions of the recipe, gets copied in Instructions
     * */
    public Recipe(String name, String categoryType, ArrayList<String> ingredientList, ArrayList<String> instructionList){
        recipeName = name;
        category = categoryType;

        ingredList = new Ingredients(ingredientList);
        instructList = new Instructions(instructionList);

        recipeID = id++;
    }

    /**
     * Private constructor for returning copies of a Recipe
     *
     * @param name the name of the recipe
     * @param categoryType the category the recipe falls under
     * @param ingredientList a string[] of the ingredients of the recipe, gets copied in Ingredients
     * @param instructionList a string[] of the instructions of the recipe, gets copied in Instructions
     * */
    private Recipe(String name, String categoryType, ArrayList<String> ingredientList, ArrayList<String> instructionList, int rID){
        recipeName = name;
        category = categoryType;

        ingredList = new Ingredients(ingredientList);
        instructList = new Instructions(instructionList);

        recipeID = rID;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return the Instructions
     * */
    public Instructions getInstructList(){
        return  instructList;
    }

    /**
     * @return the Ingredient list of the recipe
     * */
    public  Ingredients getIngredList(){
        return  ingredList;
    }

    /**
     * @return a deepCopy of the current recipe
     *
     * */
    public Recipe deepCopy(){
        return new Recipe(recipeName, category, ingredList.getIngredientList(), instructList.getInstructionList(), recipeID);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
