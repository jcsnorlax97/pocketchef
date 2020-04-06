package net.team5.pocketchef.Database.hsqldb;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.CategoryPersistence;
import net.team5.pocketchef.MainActivity;

import org.hsqldb.jdbc.JDBCArrayBasic;

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

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + this.dbPath + ";shutdown=true", "SA", "");
    }

    /** Create Category object from DB result **/
    private Category fromResultSet(final ResultSet rs) throws SQLException {
        final String category = rs.getString("CNAME");

        /** Transform Object[] to ArrayList **/
        Object[] recipeObject = (Object[]) rs.getArray("RID").getArray();
        ArrayList<Integer> recipeArray = toArray(recipeObject);
        final ArrayList<RecipeObject> recipeObjects = getRecipes(recipeArray);

        return new Category(category, recipeObjects);
    }

    /** Convert Arraylist to array **/
    private Integer[] getArray(ArrayList<?> list)
    {
        Integer[] array = list.toArray(new Integer[list.size()]);
        return array;
    }

    /** Convert Arraylist<RecipeObject> to arrayList<String> **/
    private ArrayList<Integer> convertRecipeArray(ArrayList<RecipeObject> list)
    {
        ArrayList<Integer> newArray = new ArrayList<>();
        for(int x = 0; x < list.size(); x++)
        {
            newArray.add(list.get(x).getRecipeId());
        }
        return newArray;
    }

    /** There is no conversion for HSQLDB, have to do manually **/
    private ArrayList<Integer> toArray(Object[] recipeObject)
    {
        ArrayList<Integer> array = new ArrayList<>();
        for (int x = 0; x < recipeObject.length; x++)
        {
            array.add((Integer)recipeObject[x]);
        }
        return array;
    }

    /** Get the RecipeObjects related to the Category **/
    private ArrayList<RecipeObject> getRecipes(ArrayList<Integer> recipeArray)
    {
        ArrayList<RecipeObject> recipeObjects = new ArrayList<>();
        for(int x = 0; x < recipeArray.size(); x++)
        {
            RecipeObject recipe = MainActivity.manager.getRecipe(recipeArray.get(x));
            if(recipe != null)
                recipeObjects.add(recipe);
        }
        return recipeObjects;
    }

    /**
    * Responsibilities:
    *  - create new Category and add that to DB
    *  - Reference Note: Only developers are allowed to make new categories.
    */
    public Category createCategory(Category category)
    {
        /** Used for transforming an array to an SQL array **/
        org.hsqldb.types.Type type = org.hsqldb.types.Type.SQL_INTEGER;
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO CATEGORY VALUES(?, ?)");
            st.setString(1, category.getCategoryName());
            JDBCArrayBasic array = new JDBCArrayBasic(getArray(convertRecipeArray(category.getRecipeList())), type);
            st.setArray(2, array);
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
    public Category appendRecipeList(Category category, RecipeObject recipe)
    {
        /*** delete category and reinsert due to problems with SQL Arrays ***/
        deleteCategory(category);
        category.appendRecipeList(recipe);
        createCategory(category);

        return category;
    }

    /**
     * Responsibilities:
     *  - delete Recipe from Category
     */
    public Category deleteRecipe(Category category, RecipeObject recipe)
    {
        /*** delete category and reinsert due to problems with SQL Arrays ***/
        deleteCategory(category);
        category.deleteRecipe(recipe);
        createCategory(category);

        return category;
    }

    /**
     * Responsibilities:
     *  - delete category
     */
    public void deleteCategory(Category category)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("DELETE FROM CATEGORY WHERE CNAME = ?");
            st.setString(1, category.getCategoryName());
            st.executeUpdate();
        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
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
