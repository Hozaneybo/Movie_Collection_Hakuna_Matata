package dal.Idal;

import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Date;
import java.util.List;

public interface IMovieDataAccess {
    public List<Movie> getAllMovie() throws SQLServerException;

    Movie createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception;

    public void deleteMovie(Movie movie) throws Exception;
    public void updatePersonalRating(int movieId, double personalRating) throws Exception;
    public void updateDate(int movieId, Date currentDate) throws Exception;

}

