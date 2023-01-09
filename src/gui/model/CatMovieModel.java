package gui.model;

import bll.CatMovieManager;
import dal.db.CatMovieDAO;

import java.sql.SQLException;
import java.util.List;

public class CatMovieModel {
    private CatMovieManager catMovieManager;

    public CatMovieModel() {
        catMovieManager = new CatMovieManager();
    }

    public void setCategories(int movieID, List<Integer> categoryIDs) throws SQLException {
        catMovieManager.setCategories(movieID, categoryIDs);
    }

    public List<Integer> getCategories(int movieID) throws SQLException {
        return catMovieManager.getCategories(movieID);
    }
}
