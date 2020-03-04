package net.team5.pocketchef.Database.hsqldb;

import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Database.IngredientPersistence;

public class IngredientHandler implements IngredientPersistence {

    /**
    * Responsibilities:
    *  - add ingredient to DB
    *  - Referencing Note (for persistence layer):
    *      - if there exists the ingredient in DB, do nothing;
    *        else add the ingredient to DB.
    */
    @Override
    public void addIngredient(Ingredient ingredient)
    {

    }

    /**
    * Responsibilities:
    *  - Retrieve an ingredient from DB and return to caller
    *  - Remark: ingredientName is the primary key for Ingredient table.
    */
    @Override
    public Ingredient getIngredient(String ingredientName)
    {
        return null;
    }

}
