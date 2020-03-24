package net.team5.pocketchef.Database.stubs;


import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;

import java.util.ArrayList;

public class CategoryPersistenceStub implements CategoryPersistence {

    /********************************************************
     * Instance Variables
     ********************************************************/
    private ArrayList<Category> categories;

    /********************************************************
     * Constructors
     ********************************************************/

    /**
     * assumption:
     *  1. when the application begins and retrieve the categories from the database,
     *     it is getting the names only. As a result, we should create Category using
     *     the category name argument only, and put `null` for the recipeList argument.
     */

    /**
     * CategoryPersistenceStub [Done]
     */
    public CategoryPersistenceStub() {
        this.categories = new ArrayList<Category>();

        // pretend we retrieve three category names, "Vegan", "Mexican", and "Indian", from the database; we create Category based on the names.
        this.categories.add(new Category("Vegan", null));
        this.categories.add(new Category("Mexican", null));
        this.categories.add(new Category("Indian", null));
    }

    /********************************************************
     * Instance Methods
     ********************************************************/

    /**
     * assumptions:
     *  1. The category name is CASE SENSITIVE, which means "Asian" != "asian".
     *  2. getCategory() returns null if it cannot find the name.
     *
     * questions:
     *  1. in createCategory(), why is this returning a Category? It is a bit trivial that we pass a Category as argument, and then return the same Category.
     *  2.
     */

    /**
     * createCategory() [Done]
     *
     * This inserts new Category into the Category table.
     *
     * Responsibilities:
     *  - create new Category and add that to DB
     *  - Reference Note: in iteration 2, only developers are allowed to make new category.
     */
    public Category createCategory(Category category) {
        this.categories.add(category);
        return category;
    }

    /**
     * appendRecipeList()
     *
     * Responsibilities:
     *  - append new Recipe into the target Category
     */
    public Category appendRecipeList(Category category, RecipeObject recipe) {
        if (category != null) {
            category.appendRecipeList(recipe);
            return category;
        }
        return null;
    }

    /**
     * getCategories() [Done]
     *
     * Responsibilities:
     *  - retrieve the whole list of categories and return back to callers
     */
    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    /**
     * getCategory() [Done]
     *
     * Responsibilities:
     *  - Retrieve a category from DB and return to caller
     *  - Remark: catagoryName is the primary key for Ingredient table.
     */
    public Category getCategory(String categoryName) {

        // try to find the Category having the same name as the argument.
        for (Category category : this.getCategories()) {
            if (categoryName.equals(category.getCategoryName())) {
                return category;
            }
        }

        // return null when no such categoryName in the list.
        return null;
    }

}
