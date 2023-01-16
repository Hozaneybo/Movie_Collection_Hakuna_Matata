package dal.db;

import be.Category;
import dal.Idal.ICategoryDataAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDataAccess {
    // Declare a private variable to store the DBConnector object
    private DBConnector dbConnector;

    // Initialize the dbConnector object in the constructor
    public CategoryDAO(){
        dbConnector = new DBConnector();
    }

    @Override
    public List<Category> getAllCategories() {
        // Create an empty list to store the categories
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM Category;";
        try (Connection connection = dbConnector.getConnection()) {

            // Create a statement to execute the SQL query
            Statement statement = connection.createStatement();

            // Execute the SQL query and get the result set
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                // Iterate through the result set and create Category objects for each row
                while (resultSet.next()) {
                    int categoryId = resultSet.getInt("id");
                    String categoryName = resultSet.getString("Name");
                    Category newCategory = new Category(categoryId, categoryName);
                    categoryList.add(newCategory);
                }
            }
        } catch (SQLException e) {
            // Throw a runtime exception if there's an error
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public Category addCategory(String name) throws Exception {
        String sql = "INSERT INTO Category (Name) VALUES (?);";

        // Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            // Create a prepared statement to execute the SQL query
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,name);

            // Execute the SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create a Category object and return it
            Category category = new Category(id, name);
            return category;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create a Category", ex);
        }
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        String sql = "DELETE FROM Category WHERE Name = (?) AND Id = (?);";

        // Get connection to the database
        try (Connection conn = dbConnector.getConnection()) {
            // Create a prepared statement to execute the SQL query
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());

            // Execute the SQL statement
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete the Category", ex);
        }
    }
}