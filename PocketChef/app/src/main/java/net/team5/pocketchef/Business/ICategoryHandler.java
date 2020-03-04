package net.team5.pocketchef.Business;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.RecipeObject;

import java.util.List;

public interface ICategoryHandler {

    /**
     * createCategory
     *
     * Responsibilities:
     *  - create new Category and add that to DB
     *  - Reference Note: in iteration 2, only developers are allowed to make new category.
     */
    void createCategory(String categoryName);

    /**
     * appendRecipeList
     *
     * Responsibilities:
     *  - append new Recipe into the target Category
     */
    void appendRecipeList(Category category, RecipeObject recipe);

    /**
     * getCategories
     *
     * Responsibilities:
     *  - retrieve the whole list of categories and return back to callers
     */
    List<Category> getCategories();

}
