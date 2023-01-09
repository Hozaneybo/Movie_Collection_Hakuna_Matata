package gui.model;

public class MCModel {
    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private CatMovieModel catMovieModel;

    public MCModel() throws Exception {
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
        catMovieModel = new CatMovieModel();
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

    public CatMovieModel getCatMovieModel() {
        return catMovieModel;
    }

    public void setCatMovieModel(CatMovieModel catMovieModel) {
        this.catMovieModel = catMovieModel;
    }
}