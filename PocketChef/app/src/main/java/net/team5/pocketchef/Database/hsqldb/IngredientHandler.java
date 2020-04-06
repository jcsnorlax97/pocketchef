package net.team5.pocketchef.Database.hsqldb;

import net.team5.pocketchef.Business.Objects.Ingredient;
import net.team5.pocketchef.Database.IngredientPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientHandler implements IngredientPersistence {

    private final String dbPath;

    public IngredientHandler(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + this.dbPath + ";shutdown=true", "SA", "");
    }

    private Ingredient fromResultSet(final ResultSet rs) throws SQLException {
        final String ingredient = rs.getString("INAME");
        return new Ingredient(ingredient);
    }

    /**
    * Responsibilities:
    *  - add ingredient to DB
    *  - Referencing Note (for persistence layer):
    *      - if there exists the ingredient in DB, do nothing;
    *        else add the ingredient to DB.
    */
    public Ingredient addIngredient(Ingredient ingredient)
    {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO INGREDIENT VALUES(?)");
            st.setString(1, ingredient.getIngredientName());
            st.executeUpdate();

            return ingredient;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Responsibilities:
     *  - delete ingredient from DB
     */
    public void deleteIngredient(Ingredient ingredient)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("DELETE FROM INGREDIENT WHERE INAME = ?");
            st.setString(1,  ingredient.getIngredientName());
            st.executeUpdate();
        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
    * Responsibilities:
    *  - Retrieve an ingredient from DB and return to caller
    *  - Remark: ingredientName is the primary key for Ingredient table.
    */
    public Ingredient getIngredient(String ingredientName)
    {
        final Ingredient ingredient;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT FROM INGREDIENT WHERE INAME = ?");
            st.setString(1, ingredientName);
            final ResultSet rs = st.executeQuery();
            ingredient = fromResultSet(rs);
            rs.close();
            st.close();

            return ingredient;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    /**
    * Responsibilities:
    *  - retrieve the whole list of ingredients and return back to callers
    */
    public ArrayList<Ingredient> getIngredients()
    {
        final ArrayList<Ingredient> ingredients = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM INGREDIENT");
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final Ingredient ingredient = fromResultSet(rs);
                ingredients.add(ingredient);
            }
            rs.close();
            st.close();

            return ingredients;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }
}
