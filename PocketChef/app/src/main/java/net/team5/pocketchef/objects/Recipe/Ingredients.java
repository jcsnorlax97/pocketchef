package net.team5.pocketchef.objects.Recipe;

import java.util.ArrayList;

/**
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 1
 * John Hiebert
 * Feb 1 2020
 *
 * Class for storing the ingredients of a particular recipe
 *
 * Contains:
 * --Variables--
 * ArrayList<String> ingredientList: an array of strings that stores the ingredients
 *
 * --Methods--
 * */
public class Ingredients {

    private ArrayList<String> ingredientList;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param ingredList an ArrayList of strings storing the ingredients of the recipe. Copies of the string values
     * */
    public Ingredients(ArrayList<String> ingredList){
        ArrayList<String> ingredientList = new ArrayList<String>(ingredList);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return returns the String[] of the instructionsList
     * */
    public ArrayList<String> getIngredientList(){
        return  new ArrayList<String>(ingredientList);
    }

    /**
     * @return returns a deepCopy of this Ingredient instance
     * */
    public Ingredients deepCopy(){
        //Constructor makes a copy of the ingredientList
        return new Ingredients(ingredientList);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
