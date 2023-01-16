package bll;

import be.Movie;
import dal.Idal.IMovieDataAccess;
import dal.db.MovieDAO;
import dal.util.MovieSearcher;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MovieManager {
    // Declare a private variable to store the MovieSearcher object
    private MovieSearcher movieSearcher = new MovieSearcher();

    // Declare a private variable to store the IMovieDataAccess object
    private IMovieDataAccess movieDAO;

    // Initialize the movieDAO object in the constructor
    public MovieManager() {
        movieDAO = new MovieDAO();
    }

    // Method to get all movies from the movieDAO object
    public List<Movie> getAllMovies() throws SQLException {
        return movieDAO.getAllMovie();
    }

    // Method to create a movie using the movieDAO object
    public Movie createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception {
        return movieDAO.createMovie(name, fileLink, personalRating, IMDBRating, lastView);
    }

    // Method to delete a movie using the movieDAO object
    public void deleteMovie(Movie movie) throws Exception {
        movieDAO.deleteMovie(movie);
    }

    // Method to search for movies using the movieSearcher object
    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovies = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }

    // Method to update the personal rating of a movie using the movieDAO object
    public void updatePersonalRating(int movieId, double personalRating) throws Exception {
        movieDAO.updatePersonalRating(movieId, personalRating);
    }

    // Method to update the last view date of a movie using the movieDAO object
    public void updateDate(int movieId, Date currentDate) throws Exception {
        movieDAO.updateDate(movieId, currentDate);
    }
    public int getMovieIdByName(String name) throws SQLException
    {
        return movieDAO.getMovieIdByName(name);
    }
}
