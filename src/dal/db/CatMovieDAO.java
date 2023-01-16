package dal.db;

import be.CatMovie;
import dal.Idal.ICatMovieDataAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO implements ICatMovieDataAccess {
    // Declare a private variable to store the DBConnector object
    private DBConnector dbConnector;

    // Initialize the dbConnector object in the constructor
    public CatMovieDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public void addCatMovie(CatMovie catMovie) throws SQLException {
        // Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            // Create a prepared statement to execute the SQL query
            String sql = "INSERT INTO cat_movie (category_id, movie_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Bind parameters
            statement.setInt(1, catMovie.getCategoryId());
            statement.setInt(2, catMovie.getMovieId());

            // Execute the SQL statement
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteCatMovie(int id) throws SQLException {
        // Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            // Create a prepared statement to execute the SQL query
            String sql = "DELETE FROM cat_movie WHERE movie_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Bind parameters
            statement.setInt(1, id);

            // Execute the SQL statement
            statement.executeUpdate();
        }
    }

    @Override
    public List<CatMovie> getAllCatMovies() throws SQLException {
        // Create an empty list to store the CatMovie objects
        List<CatMovie> catMovies = new ArrayList<>();

        // Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            // Create a statement to execute the SQL query
            String sql = "SELECT * FROM cat_movie";
            Statement statement = connection.createStatement();

            // Execute the SQL query and get the result set
            ResultSet resultSet = statement.executeQuery(sql);

            // Iterate through the result set and create CatMovie objects for each row
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("category_id");
                int movieId = resultSet.getInt("movie_id");
                catMovies.add(new CatMovie(id, categoryId, movieId));
            }
        }
        return catMovies;
    }

    @Override
    public List<CatMovie> getCatMoviesByMovieId(int movieId) throws SQLException {
        // Create an empty list to store the CatMovie objects

        List<CatMovie> catMovies = new ArrayList<>();
        // Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            // Create a prepared statement to execute the SQL query
            String sql = "SELECT * FROM cat_movie WHERE movie_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Bind parameters
            statement.setInt(1, movieId);

            // Execute the SQL query and get the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and create CatMovie objects for each row
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("category_id");
                catMovies.add(new CatMovie(id, categoryId, movieId));
            }
        }
        return catMovies;
    }
}