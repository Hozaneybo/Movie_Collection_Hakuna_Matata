package gui.model;

import be.Category;
import bll.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CategoryModel {

    private CategoryManager categoryManager;
    private ObservableList<Category> categoriesToBeViewed;
    private Category selectedCategory;

    public CategoryModel() throws SQLException {
        categoryManager = new CategoryManager();
        categoriesToBeViewed = FXCollections.observableArrayList();
        if(categoryManager.getAllCategories().size() != 0)
            categoriesToBeViewed.setAll(categoryManager.getAllCategories());

    }

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

    public void createCategory(String name) throws Exception {
        Category category = categoryManager.createCategory(name);
        categoriesToBeViewed.add(category);
    }


    public void deleteCategory(Category category) throws Exception {
        categoryManager.deleteCategory(category);
        categoriesToBeViewed.remove(category);
    }


    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
}
