package bll;

import be.Movie;
import dal.Idal.IMovieDataAccess;
import dal.db.MovieDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MovieManager {


    IMovieDataAccess movieDAO_DB;

    /**
     * Creates AllPlaylistsDAO_DB to be able to use methods from AllPlaylistsDAO_DB class
     */
    public MovieManager() {
        movieDAO_DB = new MovieDAO();
    }

    public List<Movie> getAllMovie() throws SQLException {
        return movieDAO_DB.getAllMovie();
    }

    public Movie createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception {
        return movieDAO_DB.createMovie(name, fileLink, personalRating, IMDBRating, lastView);
    }
    /**
     *
     * Returns the value of the deletedPlaylist method from the AllPlaylistsDAO_DB class
     * @throws Exception
     */
    public void deleteMovie (Movie movie) throws Exception {
        movieDAO_DB.deleteMovie(movie);
    }

}
