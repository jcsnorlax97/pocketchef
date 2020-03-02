package net.team5.pocketchef.Business;

import net.team5.pocketchef.Business.Objects.RecipeObject;

import java.util.List;

public interface IRecipeHandler {

    /**
     * addRecipe
     *
     * Responsibilities:
     *  - validate category inside of the recipe object exists in the database
     *    (For iteration 2, we're having fixed categories, i.e. users CANNOT add new categories)
     *  - (?) parse instructions line-by-line (optional for now)
     *  - add recipe to DB
     */
    void addRecipe(RecipeObject recipe);

    /**
     * searchRecipes
     *
     * Responsibilities:
     *  - Search ALL recipes having the target recipe name and return to callers
     *  - Remark: search based on recipe name
     */
    List<RecipeObject> searchRecipes(String recipeName);

    /**
     * searchRecipes
     *
     * Responsibilities:
     *  - Search a SINGLE recipe having the target recipe id and return to callers
     *  - Remark: search based on recipe id, which should be an UNIQUE attribute
     */
    RecipeObject searchRecipe(String recipeId);

    /**
     * deleteRecipe
     *
     * Responsibilities:
     *  - Delete a SINGLE recipe having the target recipe id.
     *  - Remark: search based on recipe id, which should be an UNIQUE attribute
     */
    void deleteRecipe(String recipeId);

}
