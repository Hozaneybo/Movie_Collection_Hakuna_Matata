package gui.model;

/**
 * The MCModel class is responsible for holding and managing instances of the
 * MovieModel, CategoryModel, and CatMovieModel classes. It also provides
 * getter and setter methods for these instances.
 */
public class MCModel {

    // Fields to hold instances of the three model classes
    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private CatMovieModel catMovieModel;

    /**
     * Constructor that creates new instances of the three model classes
     * and assigns them to the corresponding fields.
     */
    public MCModel() throws Exception {
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
        catMovieModel = new CatMovieModel();
    }

    // Getter method for the movieModel field
    public MovieModel getMovieModel() {
        return movieModel;
    }

    // Setter method for the movieModel field
    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    // Getter method for the categoryModel field
    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    // Setter method for the categoryModel field
    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    // Getter method for the catMovieModel field
    public CatMovieModel getCatMovieModel() {
        return catMovieModel;
    }

    // Setter method for the catMovieModel field
    public void setCatMovieModel(CatMovieModel catMovieModel) {
        this.catMovieModel = catMovieModel;
    }
}
