package gui.model;

public class MCModel {
    private MovieModel movieModel;
    private CategoryModel categoryModel;

    public MCModel() throws Exception {
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
    }


    public MovieModel getMovieModel()
    {
        return movieModel;
    }


    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }


    public CategoryModel getCategoryModel() {
        return categoryModel;
    }


    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

}