package net.team5.pocketchef.Application;

import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.Database.IngredientPersistence;
import net.team5.pocketchef.Database.RecipePersistence;
import net.team5.pocketchef.Database.hsqldb.CategoryHandler;
import net.team5.pocketchef.Database.hsqldb.IngredientHandler;
import net.team5.pocketchef.Database.hsqldb.RecipeHandler;
import net.team5.pocketchef.Database.stubs.CategoryPersistenceStub;
import net.team5.pocketchef.Database.stubs.IngredientPersistenceStub;
import net.team5.pocketchef.Database.stubs.RecipePersistenceStub;

public class Services
{
    private static RecipePersistence recipePersistence = null;
    private static CategoryPersistence categoryPersistence = null;
    private static IngredientPersistence ingredientPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence()
    {
        if (Services.recipePersistence == null)
        {
            Services.recipePersistence = new RecipePersistenceStub(Services.getCategoryPersistence(), Services.getIngredientPersistence());
            // Services.recipePersistence = new RecipeHandler(Main.getDBPathName());
        }

        return Services.recipePersistence;
    }

    public static synchronized CategoryPersistence getCategoryPersistence()
    {
        if (Services.categoryPersistence == null)
        {
            Services.categoryPersistence = new CategoryPersistenceStub();
            // Services.categoryPersistence = new CategoryHandler(Main.getDBPathName());
        }

        return Services.categoryPersistence;
    }

    public static synchronized IngredientPersistence getIngredientPersistence()
    {
        if (Services.ingredientPersistence == null)
        {
            Services.ingredientPersistence = new IngredientPersistenceStub();
            // Services.ingredientPersistence = new IngredientHandler(Main.getDBPathName());
        }

        return Services.ingredientPersistence;
    }
}

