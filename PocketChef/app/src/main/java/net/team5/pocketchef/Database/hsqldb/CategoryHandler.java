package net.team5.pocketchef.Database.hsqldb;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;

import java.util.List;

public class CategoryHandler implements CategoryPersistence {

    /**
    * Responsibilities:
    *  - create new Category and add that to DB
    *  - Reference Note: in iteration 2, only developers are allowed to make new category.
    */
    @Override
    public void createCategory(String categoryName)
    {

    }

    /**
    * Responsibilities:
    *  - append new Recipe into the target Category
    */
    @Override
    public void appendRecipeList(Category category, RecipeObject recipe)
    {

    }

    /**
    * Responsibilities:
    *  - retrieve the whole list of categories and return back to callers
    */
    @Override
    public List<Category> getCategories()
    {
        return null;
    }

}
