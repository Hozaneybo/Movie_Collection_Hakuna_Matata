package gui.model;


import be.Category;
import bll.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public class CategoryModel {
    private CategoryManager categoryManager;
    private ObservableList<Category> categoriesToBeViewed;

    public CategoryModel() throws SQLException {
        categoryManager = new CategoryManager();
        categoriesToBeViewed = FXCollections.observableArrayList();
        if(categoryManager.getAllCategories().size() != 0)
            categoriesToBeViewed.setAll(categoryManager.getAllCategories());

    }

    /**
     * Get all categories as an observable list
     * @return observable list of categories
     */
    public ObservableList<Category> getObservableAllCategories()
    {
        categoriesToBeViewed.clear();
        try {
            categoriesToBeViewed.setAll(categoryManager.getAllCategories());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoriesToBeViewed;
    }

    /**
     * Create a new category with the given name
     * @param name Name of the category to create
     * @throws Exception if the category could not be created
     */
    public void createCategory(String name) throws Exception {
        Category category = categoryManager.createCategory(name);
        categoriesToBeViewed.add(category);
    }

    /**
     * Delete the given category
     * @param category Category to delete
     * @throws Exception if the category could not be deleted
     */
    public void deleteCategory(Category category) throws Exception {
        categoryManager.deleteCategory(category);
        categoriesToBeViewed.remove(category);
    }

}