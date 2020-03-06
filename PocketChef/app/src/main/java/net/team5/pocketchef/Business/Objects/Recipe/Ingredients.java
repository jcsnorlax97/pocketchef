package net.team5.pocketchef.Business.Objects.Recipe;

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
     * null constructor
     * */
    public Ingredients(){
        ingredientList = null;
    }


    /**
     * @param ingredList an ArrayList of strings storing the ingredients of the recipe. Copies of the string values
     * */
    public Ingredients(ArrayList<String> ingredList){
        ingredientList = ingredList;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return returns the arrayList of the instructionsList (copy)
     * */
    public ArrayList<String> getIngredientList(){
        if(ingredientList != null)
            return  new ArrayList<String>(ingredientList);
        else
            return null;
    }

    /**
     * @return returns a deepCopy of this Ingredient instance
     * */
    public Ingredients deepCopy(){
        //Constructor makes a copy of the ingredientList
        return new Ingredients(ingredientList);
    }

    /**
     * checks to see if i2 is equal to 'this'
     *
     * @return true if the two Ingredient lists are equal
     * */
    public boolean equals(Ingredients i2){

        //check null
        if(i2 == null)
            return false;

        ArrayList<String> i2List = i2.getIngredientList();

        //check to make sure they are the same length
        if(i2List.size() != ingredientList.size())
            return false;

        //go through the list
        for(int i = 0; i < ingredientList.size(); i++){

            //if not equal return false
            if(!ingredientList.get(i).equals(i2List.get(i)))
                return false;
        }

        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
