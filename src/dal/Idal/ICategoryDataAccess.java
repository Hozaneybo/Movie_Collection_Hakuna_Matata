package dal.Idal;

import be.Category;

import java.util.List;

public interface ICategoryDataAccess {
    public List<Category> getAllCategories();
    public Category addCategory(String name) throws Exception;
    public void deleteCategory(Category category) throws Exception;
}
