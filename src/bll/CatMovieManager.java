package bll;

import be.CatMovie;
import dal.db.CatMovieDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatMovieManager {

    // Declare a private variable to store the CatMovieDAO object
    private CatMovieDAO catMovieDAO;

    // Initialize the catMovieDAO object in the constructor
    public CatMovieManager() {
        catMovieDAO = new CatMovieDAO();
    }

    // Method to set the categories for a movie
    public void setCategories(int movieID, List<Integer> categoryIDs) throws SQLException {
        // Delete any existing movie-category relationships for the movie
        List<CatMovie> existingRelationships = catMovieDAO.getCatMoviesByMovieId(movieID);
        for (CatMovie cm : existingRelationships) {
            catMovieDAO.deleteCatMovie(cm.getId());
        }
        // Add new movie-category relationships for the movie
        for (int categoryId : categoryIDs) {
            CatMovie cm = new CatMovie(0, categoryId, movieID);
            catMovieDAO.addCatMovie(cm);
        }
    }

    // Method to delete a movie-category relationship
    public void deleteCatMovie(int id) throws SQLException {
        catMovieDAO.deleteCatMovie(id);
    }

    // Method to get the categories for a movie
    public List<Integer> getCategories(int movieID) throws SQLException {
        // Return a list of category IDs for the movie
        List<CatMovie> relationships = catMovieDAO.getCatMoviesByMovieId(movieID);
        List<Integer> categoryIds = new ArrayList<>();
        for (CatMovie cm : relationships) {
            categoryIds.add(cm.getCategoryId());
        }
        return categoryIds;
    }

}
