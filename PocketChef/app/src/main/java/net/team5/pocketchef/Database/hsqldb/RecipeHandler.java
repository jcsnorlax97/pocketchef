package net.team5.pocketchef.Database.hsqldb;

import net.team5.pocketchef.Business.Objects.Category;
import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Business.Objects.RecipeObject;
import net.team5.pocketchef.Database.RecipePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeHandler implements RecipePersistence {

    private final String dbPath;

    public RecipeHandler(final String dbPath) {
        this.dbPath = dbPath;
    }

    // TODO: Set proper url, ensure path is correct, get password?
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    // TODO: Get Objects from ID's to Create RecipeObject
    private RecipeObject fromResultSet(final ResultSet rs) throws SQLException {
        private final String recipeId = rs.getString("RID");
        private final String recipeName = rs.getString("RNAME");
        private final String recipeCategory  = rs.getString("CNAME");;
        private final String[] recipeInstructions = rs.getArray("INSTRUCTIONS");
        private final List<Ingredient> recipeIngredients = rs.getArray("INGREDIENTS");
        return new RecipeObject(recipeId, recipeName, recipeCategory, recipeInstructions, recipeIngredients);
    }

    /**
    * Responsibilities:
    *  - Search ALL recipes having the target recipe name and return to callers
    *  - Remark: search based on recipe name
    */
    @Override
    public List<RecipeObject> getRecipes(String recipeName) {
        final List<RecipeObject> recipes = new ArrayList<>();

        try (final Connection c = connection()) {
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
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
    * Responsibilities:
    *  - validate category inside of the recipe object exists in the database
    *    (For iteration 2, we're having fixed categories, i.e. users CANNOT add new categories)
    *  - (?) parse instructions line-by-line (optional for now)
    *  - add recipe to DB
    */
    @Override
    public RecipeObject addRecipe(RecipeObject recipe) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO RECIPE VALUES(?, ?, ?, ?)");
            st.setString(1, recipe.getRecipeId());
            st.setString(2, recipe.getRecipeName());
            st.setString(3, recipe.getRecipeCategory().getCategoryName());
            st.setArray(4, c.createArrayOf("VARCHAR(100)", recipe.getRecipeInstructions()));
            // To fix later
            // st.setArray(5, c.createArrayOf("VARCHAR(20)", recipe.getRecipeIngredients()));

            st.executeUpdate();

            return recipe;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }

    /**
    * deleteRecipe
    *
    * Responsibilities:
    *  - Delete a SINGLE recipe having the target recipe id.
    *  - Remark: search based on recipe id, which should be an UNIQUE attribute
    */
    @Override
    public void deleteRecipe(RecipeObject recipe) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM RECIPE WHERE RID = ?");
            st.setString(1, recipe.getRecipeId());
            st.executeUpdate();
        } catch (final SQLException e) {
            System.err.println(("Catch SQLException: " + e.getMessage()));
            throw new PersistenceException(e);
        }

    }
}

