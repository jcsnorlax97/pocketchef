package net.team5.pocketchef.ui.search_recipes;

import net.team5.pocketchef.Business.Objects.Recipe.Ingredients;
import net.team5.pocketchef.Business.Objects.Recipe.Instructions;
import net.team5.pocketchef.Business.Objects.Recipe.Recipe;

/**
 *
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 2
 * Feb 25 2020
 *
 * Class for the items that are displayed when a search is preformed
 */
public class RecipeItem
{

    private int mImageResource;//the image resource that gets displayed when displaying recipe searches
    private Recipe mRecipe;//the string that is printed next to the image (probably want this to be the recipe name but can be renamed)

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    public RecipeItem(int imageResource, Recipe recipeObj)
    {
        mImageResource = imageResource;
        mRecipe = recipeObj;
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////
    /**
     * TESTING PURPOSES ONLY
     * */
    public void changeRecipeName(String newName)
    {
        mRecipe = new Recipe("You Clicked ME!", "Testing", new Ingredients(), new Instructions());
    }

    /**
     * get the image of the recipe that is being displayed
     * */
    public int getImageResource()
    {
        return mImageResource;
    }

    /**
     * get the name of the recipe that is being displayed
     * */
    public Recipe getRecipeObj()
    {
        return mRecipe;
    }


    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
