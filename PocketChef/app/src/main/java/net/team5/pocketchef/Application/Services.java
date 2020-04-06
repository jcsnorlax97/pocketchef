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
import net.team5.pocketchef.MainActivity;

public class Services
{
    private static RecipePersistence recipePersistence = null;
    private static CategoryPersistence categoryPersistence = null;
    private static IngredientPersistence ingredientPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence()
    {
        if (Services.recipePersistence == null)
        {
            if (MainActivity.isMain)
                Services.recipePersistence = new RecipeHandler(Main.getDBPathName());
            else
                Services.recipePersistence = new RecipePersistenceStub(Services.getCategoryPersistence(), Services.getIngredientPersistence());

        }

        return Services.recipePersistence;
    }

    public static synchronized CategoryPersistence getCategoryPersistence()
    {
        if (Services.categoryPersistence == null)
        {
            if (MainActivity.isMain)
                Services.categoryPersistence = new CategoryHandler(Main.getDBPathName());
            else
                Services.categoryPersistence = new CategoryPersistenceStub();
        }

        return Services.categoryPersistence;
    }

    public static synchronized IngredientPersistence getIngredientPersistence()
    {
        if (Services.ingredientPersistence == null)
        {
            if (MainActivity.isMain)
                Services.ingredientPersistence = new IngredientHandler(Main.getDBPathName());
            else
                Services.ingredientPersistence = new IngredientPersistenceStub();
        }

        return Services.ingredientPersistence;
    }
}

