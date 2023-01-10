package dal.db;

import be.CatMovie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO {
    private DBConnector dbConnector;

    public CatMovieDAO() {
        dbConnector = new DBConnector();
    }

    public void addCatMovie(CatMovie catMovie) throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO cat_movie (category_id, movie_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, catMovie.getCategoryId());
            statement.setInt(2, catMovie.getMovieId());
            statement.executeUpdate();
        }
    }

    public void deleteCatMovie(int id) throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "DELETE FROM cat_movie WHERE movie_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<CatMovie> getAllCatMovies() throws SQLException {
        List<CatMovie> catMovies = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "SELECT * FROM cat_movie";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("category_id");
                int movieId = resultSet.getInt("movie_id");
                catMovies.add(new CatMovie(id, categoryId, movieId));
            }
            return catMovies;
        }
    }

    public List<CatMovie> getCatMoviesByMovieId(int movieId) throws SQLException {
        List<CatMovie> catMovies = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "SELECT * FROM cat_movie WHERE movie_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("category_id");
                catMovies.add(new CatMovie(id, categoryId, movieId));
            }
        }
        return catMovies;
    }

}



