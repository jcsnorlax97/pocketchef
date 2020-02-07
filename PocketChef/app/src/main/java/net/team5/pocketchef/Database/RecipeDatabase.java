package net.team5.pocketchef.Database;

import net.team5.pocketchef.objects.Recipe.Recipe;

import java.util.ArrayList;


/**
 * Comp 3350 Group Project
 * Version 1.0 for Iteration 1
 * John Hiebert
 * Feb 2 2020
 *
 * Class for storing whole recipes
 *
 * Contains:
 * --Variables--
 * ArrayList<Recipe> recipes: an arrayList that stores the recipes of the whole app
 *
 * --Methods--
 * */
public class RecipeDatabase implements RecipeStorageInterface {

    //the list of all recipes
    private ArrayList<Recipe> recipes;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////

    public RecipeDatabase(){
        recipes = new ArrayList<Recipe>();
    }

    ///////////////////////////////////////////////////////////////////////////
    // END CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return returns the Arraylist of recipes (shallow copy)
     * */
    public ArrayList<Recipe> getRecipes(){
        return new ArrayList<Recipe>(recipes);
    }

    /**
     * Searches through the list of recipes and returns an arraylist of all recipes that match (exactly)
     * the string recipeName
     *
     * @return arrayList of all the recipes that match recipeName
     * */
    public ArrayList<Recipe> searchName(String recipeName){

        ArrayList<Recipe> vaildRecipes = new ArrayList<Recipe>();//list of recipes with the same name as recipeName

        for(int i = 0; i < recipes.size(); i++){
            Recipe curr = recipes.get(i);

            //check the names
            if(curr.recipeName.equals(recipeName)){
                vaildRecipes.add(curr.deepCopy());
            }
        }

        return vaildRecipes;
    }

    /**
     * Searches through the list of recipes and returns an arraylist of all recipes that match (exactly
     * the string categoryType
     * */
    public ArrayList<Recipe> searchCategory(String categoryType){

        ArrayList<Recipe> vaildRecipes = new ArrayList<Recipe>();//list of recipes with the same name as recipeName

        for(int i = 0; i < recipes.size(); i++){
            Recipe curr = recipes.get(i);

            //check the category
            if(curr.category.equals(categoryType)){
                vaildRecipes.add(curr.deepCopy());
            }
        }

        return vaildRecipes;
    }

    public void addRecipe(Recipe newRecipe){
        //temp
        recipes.add(newRecipe);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END METHODS
    ///////////////////////////////////////////////////////////////////////////
}
