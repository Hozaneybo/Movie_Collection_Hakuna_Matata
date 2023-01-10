package bll;



import be.CatMovie;
import dal.db.CatMovieDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatMovieManager {

    private CatMovieDAO catMovieDAO;

    public CatMovieManager() {
        // create catMovieDAO object

        catMovieDAO = new CatMovieDAO();
    }

    public void setCategories(int movieID, List<Integer> categoryIDs) throws SQLException {
        // delete any existing movie-category relationships for the movie
        List<CatMovie> existingRelationships = catMovieDAO.getCatMoviesByMovieId(movieID);
        for (CatMovie cm : existingRelationships) {
            catMovieDAO.deleteCatMovie(cm.getId());
        }
        // add new movie-category relationships for the movie
        for (int categoryId : categoryIDs) {
            CatMovie cm = new CatMovie(0, categoryId, movieID);
            catMovieDAO.addCatMovie(cm);
        }
    }
    public void deleteCatMovie(int id) throws SQLException {
        catMovieDAO.deleteCatMovie(id);
    }


    public List<Integer> getCategories(int movieID) throws SQLException {
        // return a list of category IDs for the movie
        List<CatMovie> relationships = catMovieDAO.getCatMoviesByMovieId(movieID);
        List<Integer> categoryIds = new ArrayList<>();
        for (CatMovie cm : relationships) {
            categoryIds.add(cm.getCategoryId());
        }
        return categoryIds;
    }

}
