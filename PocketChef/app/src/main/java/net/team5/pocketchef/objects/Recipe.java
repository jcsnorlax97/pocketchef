package net.team5.pocketchef.objects;

import java.util.Objects;

/**
 * This Recipe class is used for representing the Recipe object.
 *
 * Notes:
 * - Instance variables are constant, i.e. they cannot be mutated.
 * - The only way change instance variables is to create new object using the constructor.
 * - (Jan/30) This is currently just a template.
 */
public class Recipe {

    private final String recipeId;
    private final String recipeName;
    private final String recipeDescription;
    private final String recipeCategory;

    /**
     * --- constructors ---
     */
    public Recipe(final String newId) {
        this.recipeId = newId;
        this.recipeName = null;
        this.recipeDescription = null;
        this.recipeCategory = null;
    }

    public Recipe(final String newId, final String newRecipeName, final String newRecipeDescription, final String newRecipeCategory) {
        this.recipeId = newId;
        this.recipeName = newRecipeName;
        this.recipeDescription = newRecipeDescription;
        this.recipeCategory = newRecipeCategory;
    }

    /**
     * --- accessors ---
     */
    public String getRecipeId() {
        return this.recipeId;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public String getRecipeDescription() {
        return this.recipeDescription;
    }

    public String getRecipeCategory() {
        return this.recipeCategory;
    }

    /**
     * --- others ---
     */
    public String toString() {
        return String.format("[Recipe] %s %s %s %s", this.recipeId, this.recipeName, this.recipeDescription, this.recipeCategory))
    }

    public int hashCode() {
        return Objects.hash(this.recipeId, this.recipeName, this.recipeDescription, this.recipeCategory);
    }

    public boolean equals(final Recipe obj) {
        return (
            Objects.equals(this.recipeId, obj.recipeId) &&
            Objects.equals(this.recipeName, obj.recipeName) &&
            Objects.equals(this.recipeDescription, obj.recipeDescription) &&
            Objects.equals(this.recipeCategory, obj.recipeCategory)
        );
    }

}
