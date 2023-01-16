package bll;

import be.Category;
import dal.Idal.ICategoryDataAccess;
import dal.db.CategoryDAO;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    // Declare a private variable to store the ICategoryDataAccess object
    private ICategoryDataAccess categoryDAO;

    // Initialize the categoryDAO object in the constructor
    public CategoryManager() {
        categoryDAO = new CategoryDAO();
    }

    // Method to get all categories from the categoryDAO object
    public List<Category> getAllCategories() throws SQLException {
        return categoryDAO.getAllCategories();
    }

    // Method to create a category using the categoryDAO object
    public Category createCategory(String name) throws Exception {
        return categoryDAO.addCategory(name);
    }

    // Method to delete a category using the categoryDAO object
    public void deleteCategory(Category category) throws Exception {
        categoryDAO.deleteCategory(category);
    }
}
