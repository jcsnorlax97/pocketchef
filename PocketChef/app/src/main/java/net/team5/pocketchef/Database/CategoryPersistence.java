package net.team5.pocketchef.Database;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import java.util.ArrayList;

public interface CategoryPersistence {
    /**
     * createCategory
     *
     * Responsibilities:
     *  - create new Category and add that to DB
     *  - Reference Note: in iteration 2, only developers are allowed to make new category.
     */
    Category createCategory(Category category);

    /**
     * appendRecipeList
     *
     * Responsibilities:
     *  - append new Recipe into the target Category
     */
    Category appendRecipeList(Category category, RecipeObject recipe);

    /**
     * Responsibilities:
     *  - delete Recipe from Category
     */
    Category deleteRecipe(Category category, RecipeObject recipe);

    /**
     * appendRecipeList
     *
     * Responsibilities:
     * delete a Category from DB
     */
    void deleteCategory(Category category);

    /**
     * getCategories
     *
     * Responsibilities:
     *  - retrieve the whole list of categories and return back to callers
     */
    ArrayList<Category> getCategories();

    /**
     * Responsibilities:
     *  - Retrieve a category from DB and return to caller
     *  - Remark: catagoryName is the primary key for Ingredient table.
     */
    Category getCategory(String categoryName);
}
