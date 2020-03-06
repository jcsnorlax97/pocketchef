package net.team5.pocketchef.Database.hsqldb;

import android.widget.Toast;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryHandler implements CategoryPersistence {

    private final String dbPath;

    public CategoryHandler(final String dbPath) {
        this.dbPath = dbPath;
    }

    // TODO: Set proper url, ensure path is correct, get password?
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + this.dbPath + ";shutdown=true", "SA", "");
    }

    /** Create Category object from DB result **/
    private Category fromResultSet(final ResultSet rs) throws SQLException {
        final String category = rs.getString("CNAME");
        ArrayList currRecipe = (ArrayList)(rs.getArray("RID").getArray());

        Integer[] recipeArray = (Integer[])currRecipe.toArray(new Integer[currRecipe.size()]);
        final ArrayList<RecipeObject> recipeObjects = getRecipes(recipeArray);
        return new Category(category, recipeObjects);
    }

    /** Get the RecipeObjects related to the Category **/
    private ArrayList<RecipeObject> getRecipes(Integer[] recipeArray)
    {
        ArrayList<RecipeObject> recipeObjects = new ArrayList<>();
        for(int x = 0; x < recipeArray.length; x++)
        {
            recipeObjects.add(MainActivity.manager.getRecipe(recipeArray[x]));
        }
        return recipeObjects;
    }

    /**
    * Responsibilities:
    *  - create new Category and add that to DB
    *  - Reference Note: in iteration 2, only developers are allowed to make new category.
    */
    public Category createCategory(Category category)
    {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO CATEGORY VALUES(?, ?)");
            st.setString(1, category.getCategoryName());
            st.setArray(2, c.createArrayOf("VARCHAR(20)", new String[1024]));
            st.executeUpdate();
            return category;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
    * Responsibilities:
    *  - append new Recipe into the target Category
    */
    //TODO: decide on if Arrays will be continued to be used (Iteration 3) before doing this
    public Category appendRecipeList(Category category, RecipeObject recipe)
    {
        return null;
    }

    /**
     * Responsibilities:
     *  - retrieve the whole list of categories and return back to callers
     */
    public ArrayList<Category> getCategories()
    {
        final ArrayList<Category> categories = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM CATEGORY");
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final Category category = fromResultSet(rs);
                categories.add(category);
            }
            rs.close();
            st.close();

            return categories;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
    * Responsibilities:
    *  - Retrieve a category from DB and return to caller
    *  - Remark: catagoryName is the primary key for Ingredient table.
    */
    public Category getCategory(String categoryName)
    {
        final Category category;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT FROM INGREDIENT WHERE CNAME = ?");
            st.setString(1, categoryName);
            final ResultSet rs = st.executeQuery();
            category = fromResultSet(rs);
            rs.close();
            st.close();

            return category;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

}
