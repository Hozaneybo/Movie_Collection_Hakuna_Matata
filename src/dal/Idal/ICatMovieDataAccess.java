package dal.Idal;

import be.CatMovie;

import java.sql.SQLException;
import java.util.List;

public interface ICatMovieDataAccess {
    void addCatMovie(CatMovie catMovie) throws SQLException;

    void deleteCatMovie(int id) throws SQLException;

    List<CatMovie> getAllCatMovies() throws SQLException;

    List<CatMovie> getCatMoviesByMovieId(int movieId) throws SQLException;
}
