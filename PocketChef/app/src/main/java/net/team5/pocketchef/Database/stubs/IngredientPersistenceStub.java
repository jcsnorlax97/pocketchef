package net.team5.pocketchef.Database.stubs;

import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Database.IngredientPersistence;

import java.util.ArrayList;

public class IngredientPersistenceStub implements IngredientPersistence {

    /********************************************************
     * Instance Variables
     ********************************************************/
    private ArrayList<Ingredient> ingredients;

    /********************************************************
     * Constructors
     ********************************************************/

    public IngredientPersistenceStub() {
        this.ingredients = new ArrayList<Ingredient>();

        // pretend we retrieve all ingredients from the DB (actual: create new ingredient and add to the ArrayList)
        // (PS: these are required for the constructor in RecipePersistenceStub.)
        this.ingredients.add(new Ingredient("Eggs"));
        this.ingredients.add(new Ingredient("Bacons"));
        this.ingredients.add(new Ingredient("Salt"));
        this.ingredients.add(new Ingredient("Pepper"));
        this.ingredients.add(new Ingredient("1% skim milk"));
        this.ingredients.add(new Ingredient("A slice of bread"));
    }

    /********************************************************
     * Instance Methods
     ********************************************************/

    /**
     * addIngredient() [Done]
     *
     * Responsibilities:
     *  - add ingredient to DB
     *  - Referencing Note (for persistence layer):
     *      - if there exists the ingredient in DB, do nothing;
     *        else add the ingredient to DB.
     */
    public Ingredient addIngredient(Ingredient ingredient) {

        // checks if ingredient is valid and it is not existing in the database.
        if (!isIngredientNull(ingredient) && !isIngredientExist(ingredient))
        {
            this.ingredients.add(ingredient);
            return ingredient;
        }

        // return null if conditions are not satisfied.
        return null;
    }

    /**
     * deleteIngredient() [Done]
     *
     * Responsibilities:
     *  - delete ingredient from DB
     */
    public void deleteIngredient(Ingredient ingredient) {
        // checks if ingredient is valid and it is existing in the database.
        if (!isIngredientNull(ingredient) && isIngredientExist(ingredient))
        {
            this.ingredients.remove(ingredient);
        }
    }

    /**
     * getIngredient() [Done]
     *
     * Responsibilities:
     *  - Retrieve an ingredient from DB and return to caller
     *  - Remark: ingredientName is the primary key for Ingredient table.
     */
    public Ingredient getIngredient(String ingredientName) {
        Ingredient foundIngredient = null;
        String formattedName = this.formatIngredientName(ingredientName);
        Ingredient currIngredient = null;
        String currFormattedIngredientName = null;

        // iterate over the faked ingredient database to see if ingredient can be found.
        for (int i=0; i<this.ingredients.size(); i++) {
            currIngredient = this.ingredients.get(i);
            currFormattedIngredientName = this.formatIngredientName(currIngredient.getIngredientName());
            if (currFormattedIngredientName.equals(formattedName)) {
                foundIngredient = currIngredient;
                break;
            }
        }

        // if can be found, return the ingredient object; otherwise, return null.
        return foundIngredient;
    }

    /**
     * getIngredients() [Done]
     */
    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    /********************************************************
     * Helper Functions
     ********************************************************/

    /**
     * formatIngredientName
     *
     * This formats an ingredient name by:
     * - trimming leading and trailing spaces, and
     * - convert string into lower cases.
     *
     * @param ingredientName    the ingredient name
     * @return                  the formatted ingredient name
     */
    private String formatIngredientName(String ingredientName) {
        return ingredientName.trim().toLowerCase();
    }

    /**
     * isIngredientNull
     *
     * This checks if an ingredient object is null or not.
     *
     * @param ingredient    an ingredient object
     * @return              a boolean value to specify if the ingredient is null
     */
    private boolean isIngredientNull(Ingredient ingredient) {
        return ingredient == null;
    }

    /**
     * isIngredientExist
     *
     * This checks if an ingredient exists in the ingredient stub database.
     *
     * @param ingredient    new ingredient to be inserted
     * @return              a boolean value to specify if the ingredient exists in the database.
     */
    private boolean isIngredientExist(Ingredient ingredient) {
        boolean isExist = false;

        for (int i=0; i<this.ingredients.size(); i++) {

            // remove all empty spaces
            String dbIngredientName = this.formatIngredientName(this.ingredients.get(i).getIngredientName());
            String newIngredientName = this.formatIngredientName(ingredient.getIngredientName());

            // compare equality without case checking
            if (dbIngredientName.equals(newIngredientName)) {
                isExist = true;
                break;
            }
        }

        return isExist;
    }

}
