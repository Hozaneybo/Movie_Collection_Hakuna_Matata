package dal.db;

import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.Idal.IMovieDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MovieDAO class implements the IMovieDataAccess interface and is responsible for interacting with the database
 * to perform CRUD operations on the Movie table.
 */
public class MovieDAO implements IMovieDataAccess {
    private DBConnector dbConnector;

    /**
     * Constructor method that initializes the DBConnector object.
     */
    public MovieDAO() {
        dbConnector = new DBConnector();
    }

    /**
     * Method that retrieves all the movies from the Movie table in the database and returns them as a list of Movie objects.
     *
     * @return List of Movie objects
     * @throws SQLServerException
     */
    @Override
    public List<Movie> getAllMovie() throws SQLServerException {
        List<Movie> movieList = new ArrayList<>();
        String sql = "SELECT * FROM Movie;";
        try (Connection connection = dbConnector.getConnection()) {

            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    //Gets the id's, names and songs and makes them into strings
                    int movieId = resultSet.getInt("id");
                    String movieName = resultSet.getString("Name");
                    String movielink = resultSet.getString("Filelink");
                    double personalRating = resultSet.getDouble("PerRating");
                    double IMDBRating = resultSet.getDouble("IMDBRating");
                    Date lastView = resultSet.getDate("LastView");

                    //Adds values from database to playlists (Arraylist)
                    Movie newMovie = new Movie(movieId, movieName, movielink, personalRating, IMDBRating, lastView);
                    movieList.add(newMovie);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }

    /**
     * Method that creates a new movie in the Movie table in the database and returns the created movie as a Movie object.
     *
     * @param name
     * @param fileLink
     * @param personalRating
     * @param IMDBRating
     * @param lastView
     * @return Movie object
     * @throws Exception
     */
    @Override
    public Movie createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception {
        String sql = "INSERT INTO Movie (Name, Filelink, PerRating, IMDBRating, LastView) VALUES (?, ?, ?, ?, ?);";

        //Get connection to the database
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, name);
            stmt.setString(2, fileLink);
            stmt.setDouble(3, personalRating);
            stmt.setDouble(4, IMDBRating);
            stmt.setDate(5, lastView);

            // Run the SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            // Create movie object and send up the layers
            Movie movie = new Movie(id, name, fileLink, personalRating, IMDBRating, lastView);
            return movie;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create a Movie", ex);
        }
    }

    /**
     * Method that deletes the specified movie from the Movie table in the database.
     *
     * @param movie
     * @throws Exception
     */
    @Override
    public void deleteMovie(Movie movie) throws Exception {
        String sql = "DELETE FROM Movie WHERE Name = (?) AND Id = (?);";

        //Get connection to database
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getId());

            //Run the SQL statement
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete the movie", ex);
        }
    }

    /**
     * Method that updates the personal rating of the specified movie in the Movie table in the database.
     *
     * @param movieId
     * @param personalRating
     * @throws Exception
     */
    public void updatePersonalRating(int movieId, double personalRating) throws Exception {
        String sql = "UPDATE Movie SET PerRating = ? WHERE Id = ?;";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setDouble(1, personalRating);
            stmt.setInt(2, movieId);

            // Run the SQL statement
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update the personal rating", ex);
        }
    }

    /**
     * Method that updates the last view date of the specified movie in the Movie table in the database.
     * @param movieId
     * @param currentDate
     * @throws Exception
     */
    @Override
    public void updateDate(int movieId, Date currentDate) throws Exception {
        String sql = "UPDATE Movie SET Lastview = ? WHERE Id = ?;";

        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setDate(1, currentDate);
            stmt.setInt(2, movieId);

            // Run the SQL statement
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update the last view date for movie with ID " + movieId, ex);
        }
    }

    /**
     * Method that retrieves the ID of the movie with the specified name from the Movie table in the database.
     * @param name
     * @return movieId
     * @throws SQLException
     */
    @Override
    public int getMovieIdByName(String name) throws SQLException {
        int id = -1;
        try  (Connection conn = dbConnector.getConnection()) {
            //connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement statement = conn.prepareStatement("SELECT Id FROM Movie WHERE Name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("Id");
            }
        }
        return id;
    }
}
