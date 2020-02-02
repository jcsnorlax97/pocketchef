package net.team5.pocketchef.Database;

import net.team5.pocketchef.objects.Recipe.Recipe;

import java.util.ArrayList;

public class RecipeDatabase implements RecipeStorageInterface {

    ArrayList<Recipe> recipes;

    public RecipeDatabase(){
        recipes = new ArrayList<Recipe>();
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
            if(curr.equals(recipeName)){
                vaildRecipes.add(curr.deepCopy());
            }
        }

        return vaildRecipes;
    }

    public ArrayList<Recipe> searchCategory(String categoryType){

        return null;
    }

    public void addRecipe(Recipe newRecipe){

    }
}
