package net.team5.pocketchef.Database.hsqldb;

import net.team5.pocketchef.Database.RecipePersistence;
import net.team5.pocketchef.Business.Objects.Recipe.Recipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipePersistenceHSQLDB implements RecipePersistence {

    private final String dbPath;

    public RecipePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Recipe fromResultSet(final ResultSet rs) throws SQLException {
        final String courseID = rs.getString("courseID");
        final String courseName = rs.getString("name");
        return new Recipe(String name, String categoryType, Ingredients ingredientList, Instructions instructionList);
    }


    @Override
    public List<Recipe> getRecipes() {
        final List<Recipe> recipes = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM courses");
            while (rs.next())
            {
                final Recipe course = fromResultSet(rs);
                recipes.add(course);
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

    @Override
    public Recipe addRecipe(Recipe recipe) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO courses VALUES(?, ?)");
            st.setString(1, currentCourse.getCourseID());
            st.setString(2, currentCourse.getCourseName());

            st.executeUpdate();

            return recipe;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM courses WHERE courseID = ?");
            st.setString(1, recipe.getCourseID());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }
}
