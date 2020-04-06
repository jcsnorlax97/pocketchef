package net.team5.pocketchef.Database;

import net.team5.pocketchef.Business.Objects.Ingredient;

import java.util.ArrayList;

public interface IngredientPersistence {

    /**
     * Responsibilities:
     *  - add ingredient to DB
     *  - Referencing Note (for persistence layer):
     *      - if there exists the ingredient in DB, do nothing;
     *        else add the ingredient to DB.
     */
    Ingredient addIngredient(Ingredient ingredient);

    /**
     * Responsibilities:
     *  - delete ingredient from DB
     */
    void deleteIngredient(Ingredient ingredient);

    /**
     * Responsibilities:
     *  - Retrieve an ingredient from DB and return to caller
     *  - Remark: ingredientName is the primary key for Ingredient table.
     */
    Ingredient getIngredient(String ingredientName);

    /**
     * Responsibilities:
     *  - Retrieve the list of ingredients
     */
    ArrayList<Ingredient> getIngredients();
}
