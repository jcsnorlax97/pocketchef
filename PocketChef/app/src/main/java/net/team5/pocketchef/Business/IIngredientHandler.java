package net.team5.pocketchef.Business;

import net.team5.pocketchef.Business.Objects.Ingredient;

public interface IIngredientHandler {

    /**
     * addIngredient
     *
     * Responsibilities:
     *  - add ingredient to DB
     *  - Referencing Note (for persistence layer):
     *      - if there exists the ingredient in DB, do nothing;
     *        else add the ingredient to DB.
     */
    void addIngredient(Ingredient ingredient);

    /**
     * getIngredient
     *
     * Responsibilities:
     *  - Retrieve an ingredient from DB and return to caller
     *  - Remark: ingredientName is the primary key for Ingredient table.
     */
    Ingredient getIngredient(String ingredientName);

}
