package be;

/**
 * The CatMovie class represents a relationship between a category and a movie.
 */
public class CatMovie {
    // ID of the relationship
    private int id;
    // ID of the category
    private int categoryId;
    // ID of the movie
    private int movieId;

    /**
     * Default constructor
     */
    public CatMovie() {
    }

    /**
     * Constructor to create a new relationship between a category and a movie
     * @param id ID of the relationship
     * @param categoryId ID of the category
     * @param movieId ID of the movie
     */
    public CatMovie(int id, int categoryId, int movieId) {
        this.id = id;
        this.categoryId = categoryId;
        this.movieId = movieId;
    }

    /**
     * Method to get the ID of the relationship
     * @return ID of the relationship
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the ID of the relationship
     * @param id New ID of the relationship
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the ID of the category
     * @return ID of the category
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Method to set the ID of the category
     * @param categoryId New ID of the category
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Method to get the ID of the movie
     * @return ID of the movie
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Method to set the ID of the movie
     * @param movieId New ID of the movie
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
