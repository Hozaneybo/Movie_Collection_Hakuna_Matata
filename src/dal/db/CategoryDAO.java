package dal.db;

import be.Category;
import be.Movie;
import dal.Idal.ICategoryDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDataAccess {
    private DBConnector dbConnector;

    public CategoryDAO(){
        dbConnector= new DBConnector();
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM Category;";
        try(Connection connection = dbConnector.getConnection()){

            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next())
                {
                    //Gets the id's, names and songs and makes them into strings
                    int categoryId = resultSet.getInt("id");
                    String categoryName = resultSet.getString("Name");

                    //Adds values from database to playlists (Arraylist)
                    Category newCategory = new Category(categoryId,categoryName);
                    categoryList.add(newCategory);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public Category addCategory(String name) throws Exception {
        String sql = "INSERT INTO Category (Name) VALUES (?);";

        //Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,name);

            // Run the SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            // Create song object and send up the layers
            Category category = new Category(id, name);
            return category;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create a Category", ex);
        }
    }

    @Override
    public void deleteCategory(Category category) throws Exception {

        String sql = "DELETE FROM Category WHERE Name = (?) AND Id = (?);";

        //Get connection to database
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());

            //Run the SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception( ex);
        }
    }
}
