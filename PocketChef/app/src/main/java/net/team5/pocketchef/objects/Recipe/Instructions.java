package net.team5.pocketchef.objects.Recipe;

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

    private String[] instructionList;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param instructList a string array storing the instructions to make a recipe
     * */
    public  Instructions(String[] instructList){
        instructionList = instructList;
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
    public String[] getInstructionList(){
        return  instructionList;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
