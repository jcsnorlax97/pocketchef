package net.team5.pocketchef.Database;

import net.team5.pocketchef.Business.Objects.Recipe.Recipe;
import java.util.List;

// Simplified for now
public interface RecipePersistence {
   List<Recipe> getRecipes();

   Recipe addRecipe(Recipe recipe);

   void deleteRecipe(Recipe recipe);
}
