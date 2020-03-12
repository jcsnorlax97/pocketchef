package net.team5.pocketchef.Application;

import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.Database.IngredientPersistence;
import net.team5.pocketchef.Database.RecipePersistence;
import net.team5.pocketchef.Database.hsqldb.CategoryHandler;
import net.team5.pocketchef.Database.hsqldb.IngredientHandler;
import net.team5.pocketchef.Database.hsqldb.RecipeHandler;

public class Services
{
    private static RecipePersistence recipePersistence = null;
    private static CategoryPersistence categoryPersistence = null;
    private static IngredientPersistence ingredientPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence()
    {
        if (Services.recipePersistence == null)
        {
            //studentPersistence = new StudentPersistenceStub();
            Services.recipePersistence = new RecipeHandler(Main.getDBPathName());
        }

        return Services.recipePersistence;
    }

    public static synchronized CategoryPersistence getCategoryPersistence()
    {
        if (Services.categoryPersistence == null)
        {
            //studentPersistence = new StudentPersistenceStub();
            Services.categoryPersistence = new CategoryHandler(Main.getDBPathName());
        }

        return Services.categoryPersistence;
    }

    public static synchronized IngredientPersistence getIngredientPersistence()
    {
        if (Services.ingredientPersistence == null)
        {
            //studentPersistence = new StudentPersistenceStub();
            Services.ingredientPersistence = new IngredientHandler(Main.getDBPathName());
        }

        return Services.ingredientPersistence;
    }
}

