package net.team5.pocketchef.objects.Recipe;

import java.util.ArrayList;

/**
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 1
 * John Hiebert
 * Feb 1 2020
 *
 * Class for storing the instructions/directions for making a recipe
 *
 * Contains:
 * --Variables--
 * String[] instructionList: a string array for storing the instructions of a recipe
 *
 * --Methods--
 * */
public class Instructions {

    private ArrayList<String> instructionList;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param instructList a string array storing the instructions to make a recipe. Copies the String values
     * */
    public  Instructions(ArrayList<String> instructList){
        instructionList =  new ArrayList<String>(instructList);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return returns the ArrayList of the instructionsList (Copy)
     * */
    public ArrayList<String> getInstructionList(){
        return new ArrayList<String>(instructionList);
    }

    /**
     * @return  returns a deepCopy of the Instruction instance
     * */
    public Instructions deepCopy(){
        //Constructor makes a copy of the instructionList
        return new Instructions(instructionList);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
