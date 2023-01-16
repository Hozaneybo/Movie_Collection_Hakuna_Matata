package gui.model;

import bll.CatMovieManager;
import java.sql.SQLException;
import java.util.List;

public class CatMovieModel {
    private CatMovieManager catMovieManager;

    public CatMovieModel() {
        catMovieManager = new CatMovieManager();
    }

    /**
     * Assign the given categories to the given movie
     *
     * @param movieID     ID of the movie to assign categories to
     * @param categoryIDs IDs of the categories to assign to the movie
     * @throws SQLException if the categories could not be set
     */
    public void setCategories(int movieID, List<Integer> categoryIDs) throws SQLException {
        catMovieManager.setCategories(movieID, categoryIDs);
    }

    /**
     * Delete the association between a category and a movie
     *
     * @param id ID of the association to delete
     * @throws SQLException if the association could not be deleted
     */
    public void deleteCatMovie(int id) throws SQLException {
        catMovieManager.deleteCatMovie(id);
    }

    /**
     * Get the categories assigned to a given movie
     *
     * @param movieID ID of the movie to get categories for
     * @return List of category IDs
     * @throws SQLException if the categories could not be retrieved
     */
    public List<Integer> getCategories(int movieID) throws SQLException {
        return catMovieManager.getCategories(movieID);
    }
}

