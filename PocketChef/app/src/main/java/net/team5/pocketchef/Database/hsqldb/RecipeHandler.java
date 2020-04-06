package net.team5.pocketchef.Database.hsqldb;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.RecipePersistence;
import net.team5.pocketchef.MainActivity;

import org.hsqldb.jdbc.JDBCArrayBasic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeHandler implements RecipePersistence
{
    private final String dbPath;

    public RecipeHandler(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + this.dbPath + ";shutdown=true", "SA", "");
    }

    private RecipeObject fromResultSet(final ResultSet rs) throws SQLException
    {
        /** Get RecipeID **/
        final int recipeId = rs.getInt("RID");

        /** Get RecipeName **/
        final String recipeName = rs.getString("RNAME");

        /** Get Category
         *  -Call the DBManager and ask for the Category object
         **/
        final Category recipeCategory = MainActivity.manager.getCategory(rs.getString("CNAME"));

        /** Get instructions
         * -Convert SQL array to String[] using .getArray()
         * and then to ArrayList<String> using getInstructions() function
         **/
        Object[] recipeField = (Object[]) rs.getArray("INSTRUCTIONS").getArray();
        String[] instructions = toArray(recipeField);
        final ArrayList<String> recipeInstructions = getInstructions(instructions);

        /** Get ingredients
         * -Convert SQL array to String[] using .getArray()
         * and then to ArrayList<Ingredient> using getIngredients() function
         **/
        recipeField = (Object[]) rs.getArray("INGREDIENTS").getArray();
        String[] ingredients = toArray(recipeField);
        final ArrayList<Ingredient> recipeIngredients = getIngredients(ingredients);

        return new RecipeObject(recipeId, recipeName, recipeCategory, recipeInstructions, recipeIngredients);
    }

    /** There is no conversion for HSQLDB, have to do manually **/
    private String[] toArray(Object[] recipeObject)
    {
        String[] array = new String[recipeObject.length];
        for (int x = 0; x < recipeObject.length; x++)
        {
            System.out.println("Adding: " + ((String)recipeObject[x]));
            array[x] = ((String) recipeObject[x]);
        }
        return array;
    }

    /********************************************************
    * SQL Methods
    ********************************************************/
    /**
     * Responsibilities:
     * - Search ALL recipes having the target recipe name and return to callers
     * - Remark: search based on recipe name
     **/
    public ArrayList<RecipeObject> getRecipes(String recipeName)
    {
        final ArrayList<RecipeObject> recipes = new ArrayList<>();

        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT FROM RECIPE WHERE RNAME = ?");
            st.setString(1, recipeName);
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final RecipeObject currRecipe = fromResultSet(rs);
                recipes.add(currRecipe);
            }
            rs.close();
            st.close();
            return recipes;
        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Responsibilities:
     * - retrieve the whole list of recipes and return back to callers
     **/
    public ArrayList<RecipeObject> getRecipes()
    {
        final ArrayList<RecipeObject> recipes = new ArrayList<>();
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM RECIPE");
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final RecipeObject recipe = fromResultSet(rs);
                recipes.add(recipe);
            }
            rs.close();
            st.close();
            return recipes;
        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Responsibilities:
     * - Search for a recipe having the target recipe ID
     * - Remark: search based on recipe ID and should only be used by CategoryHandler
     **/
    public RecipeObject getRecipe(int recipeID)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT FROM RECIPE WHERE RID = ?");
            st.setInt(1, recipeID);
            final ResultSet rs = st.executeQuery();
            final RecipeObject recipe = fromResultSet(rs);
            rs.close();
            st.close();
            return recipe;
        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * Responsibilities:
     * - validate category inside of the recipe object exists in the database
     * (For iteration 2, we're having fixed categories, i.e. users CANNOT add new categories)
     * - (?) parse instructions line-by-line (optional for now)
     * - add recipe to DB
     **/
    public RecipeObject addRecipe(RecipeObject recipe)
    {
        /** Used for transforming an array to an SQL array **/
        org.hsqldb.types.Type type = org.hsqldb.types.Type.SQL_VARCHAR_DEFAULT;
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("INSERT INTO RECIPE VALUES(?, ?, ?, ?, ?)");
            st.setInt(1, recipe.getRecipeId());
            st.setString(2, recipe.getRecipeName());
            st.setString(3, recipe.getRecipeCategory().getCategoryName());
            JDBCArrayBasic array = new JDBCArrayBasic(getArray(recipe.getRecipeInstructions()), type);
            st.setArray(4, array);
            array = new JDBCArrayBasic(getArray(convertIngredientArray(recipe.getRecipeIngredients())), type);
            st.setArray(5, array);

            st.executeUpdate();

            return recipe;
        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
     * deleteRecipe
     *
     * Responsibilities:
     * - Delete a SINGLE recipe having the target recipe id.
     * - Remark: search based on recipe id, which should be an UNIQUE attribute
     **/
    public void deleteRecipe(RecipeObject recipe)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("DELETE FROM RECIPE WHERE RID = ?");
            st.setInt(1, recipe.getRecipeId());
            st.executeUpdate();
        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /********************************************************
    * Converting methods
    ********************************************************/

    /** Convert Arraylist to array **/
    private String[] getArray(ArrayList<?> list)
    {
        String[] array = list.toArray(new String[list.size()]);
        return array;
    }

    /** Convert Arraylist to array **/
    private ArrayList<String> convertIngredientArray(ArrayList<Ingredient> list)
    {
        ArrayList<String> newArray = new ArrayList<>();
        for(int x = 0; x < list.size(); x++)
        {
            newArray.add(list.get(x).getIngredientName());
        }
        return newArray;
    }

    /** Convert String[] to ArrayList<String> **/
    private ArrayList<String> getInstructions(String[] instructionArray)
    {
        ArrayList<String> instructionList = new ArrayList<>();

        for(int x = 0; x < instructionArray.length; x++)
        {
            instructionList.add(instructionArray[x]);
        }

        return instructionList;
    }

    /** Convert String[] to ArrayList<Ingredient> **/
    private ArrayList<Ingredient> getIngredients(String[] ingredientArray)
    {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        for(int x = 0; x < ingredientArray.length; x++)
        {
            ingredientList.add(MainActivity.manager.getIngredient(ingredientArray[x]));
        }

        return ingredientList;
    }
}

