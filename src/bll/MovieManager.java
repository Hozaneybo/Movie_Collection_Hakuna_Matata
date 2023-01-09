package bll;

import be.Movie;
import dal.Idal.IMovieDataAccess;
import dal.db.MovieDAO;
import dal.util.MovieSearcher;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MovieManager {
    private MovieSearcher movieSercher = new MovieSearcher();

    IMovieDataAccess movieDAO_DB;


    public MovieManager() {
        movieDAO_DB = new MovieDAO();
    }

    public List<Movie> getAllMovie() throws SQLException {
        return movieDAO_DB.getAllMovie();
    }

    public Movie createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception {
        return movieDAO_DB.createMovie(name, fileLink, personalRating, IMDBRating, lastView);
    }

    public void deleteMovie (Movie movie) throws Exception {
        movieDAO_DB.deleteMovie(movie);
    }
    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovie = getAllMovie();
        List<Movie> searchResult = movieSercher.search(allMovie, query);
        return searchResult;
    }
    public void updatePersonalRating(int movieId, double personalRating) throws Exception {
        movieDAO_DB.updatePersonalRating(movieId, personalRating);
    }
    public void updateDate(int movieId, Date currentDate) throws Exception
    {
        movieDAO_DB.updateDate(movieId, currentDate);
    }
}
