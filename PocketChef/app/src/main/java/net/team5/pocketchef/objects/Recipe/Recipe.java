package net.team5.pocketchef.objects.Recipe;

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
 * String RecipeName: the name of the recipe
 * String Category: The category the recipe falls under (Mexican, Itilian ect)
 * String
 * Ingredients ingredList: The list of ingredients needed to make the recipe
 * Instructions instrucList: the list of instructions for how to make the recipe
 *
 * --Methods--
 *
 */
public class Recipe {

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
    }

    /**
     * @param name the name of the recipe
     * @param categoryType the category the recipe falls under
     * @param ingredientList a string[] of the ingredients of the recipe
     * @param instructionList a string[] of the instructions of the recipe
     * */
    public Recipe(String name, String categoryType, String[] ingredientList, String[] instructionList){
        recipeName = name;
        category = categoryType;

        ingredList = new Ingredients(ingredientList);
        instructList = new Instructions(instructionList);
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

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
