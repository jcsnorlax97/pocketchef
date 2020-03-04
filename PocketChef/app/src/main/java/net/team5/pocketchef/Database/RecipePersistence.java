package net.team5.pocketchef.Database;

import net.team5.pocketchef.Business.Objects.RecipeObject;
import java.util.List;

public interface RecipePersistence {

   /**
   * Responsibilities:
   *  - Search ALL recipes having the target recipe name and return to callers
   *  - Remark: search based on recipe name
   */
   List<RecipeObject> getRecipes(String recipeName);

   /**
   * Responsibilities:
   *  - validate category inside of the recipe object exists in the database
   *    (For iteration 2, we're having fixed categories, i.e. users CANNOT add new categories)
   *  - (?) parse instructions line-by-line (optional for now)
   *  - add recipe to DB
   */
   RecipeObject addRecipe(RecipeObject recipe);

   /**
   * deleteRecipe
   *
   * Responsibilities:
   *  - Delete a SINGLE recipe having the target recipe id.
   *  - Remark: search based on recipe id, which should be an UNIQUE attribute
   */
   void deleteRecipe(RecipeObject recipe);
}
