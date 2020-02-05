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
 * ArrayList<String> instructionList: a string array for storing the instructions of a recipe
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

    /**
     * checks to see if i2 is equal to 'this'
     *
     * @return true if the two Instructions lists are equal
     * */
    public boolean equals(Instructions i2){

        //check null
        if(i2 == null)
            return false;

        ArrayList<String> i2List = i2.getInstructionList();

        //check to see they are the same length
        if(i2List.size() != instructionList.size())
            return false;


        //go through whole list
        for(int i = 0; i < instructionList.size(); i++){

            if(!instructionList.get(i).equals(i2List.get(i))){
                return false;
            }
        }

        return true;
    }

    /**
     * @return returns the instructionList as a single string with each instruction on a seperate line
     * */
    public String toString(){
        String rs = "";
        for(int i = 0; i < instructionList.size(); i++){
            rs += instructionList.get(i) + "\n";
        }
        return rs;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
