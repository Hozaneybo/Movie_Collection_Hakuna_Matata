package gui.model;

import bll.CatMovieManager;
import java.sql.SQLException;
import java.util.List;

public class CatMovieModel {
    private CatMovieManager catMovieManager;

    public CatMovieModel() {
        catMovieManager = new CatMovieManager();
    }

    // Assign the given categories to the given movie
    public void setCategories(int movieID, List<Integer> categoryIDs) throws SQLException {
        catMovieManager.setCategories(movieID, categoryIDs);
    }

   // Delete the association between a category and a movie
    public void deleteCatMovie(int id) throws SQLException {
        catMovieManager.deleteCatMovie(id);
    }

    // Get the categories assigned to a given movie
    public List<Integer> getCategories(int movieID) throws SQLException {
        return catMovieManager.getCategories(movieID);
    }
}

