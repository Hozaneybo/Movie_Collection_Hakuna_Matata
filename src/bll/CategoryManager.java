package bll;

import be.Category;
import dal.Idal.ICategoryDataAccess;
import dal.db.CategoryDAO;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    ICategoryDataAccess categoryDAO;

    public CategoryManager() {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryDAO.getAllCategories();
    }

    public Category createCategory(String name) throws Exception {
        return categoryDAO.addCategory(name);
    }

    public void deleteCategory (Category category) throws Exception {
        categoryDAO.deleteCategory(category);
    }
}
