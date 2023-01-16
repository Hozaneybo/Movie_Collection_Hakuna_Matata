package gui.model;

import be.Movie;
import bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The MovieModel class is responsible for holding and managing instances of the
 * Movie class and interacting with the MovieManager class to perform actions
 * related to the management of movies.
 */
public class MovieModel {

    // Field to hold an instance of the MovieManager class
    private MovieManager movieManager;
    // ObservableList to hold a list of movies to be viewed
    private ObservableList<Movie> moviesToBeViewed;
    // Field to hold the currently selected movie
    private Movie selectedMovie;

    /**
     * Getter method for the selectedMovie field
     */
    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    /**
     * Setter method for the selectedMovie field
     */
    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    /**
     * Constructor that creates a new instance of the MovieManager class and
     * initializes the moviesToBeViewed observable list with the current list
     * of movies from the MovieManager.
     */
    public MovieModel() throws SQLException {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        if (movieManager.getAllMovies().size() != 0) {
            moviesToBeViewed.setAll(movieManager.getAllMovies());
        }
        // sort the moviesToBeViewed list by movie name
        Comparator<Movie> movieComparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(moviesToBeViewed, movieComparator);
    }

    /**
     * Getter method for the moviesToBeViewed observable list
     */
    public ObservableList<Movie> getObservableAllMovies() {
        moviesToBeViewed.clear();
        try {
            moviesToBeViewed.setAll(movieManager.getAllMovies());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return moviesToBeViewed;
    }

    /**
     * Method that uses the MovieManager to delete a movie, and removes it
     * from the moviesToBeViewed observable list.
     */
    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);
        moviesToBeViewed.remove(movie);
    }

    /**
     * Method that uses the MovieManager to update the personal rating of a movie.
     */
    public void updatePersonalRating(int movieId, double personalRating) throws Exception {
        movieManager.updatePersonalRating(movieId, personalRating);
    }

    /**
     * Method that uses the MovieManager to retrieve the ID of a movie by its name.
     */
    public int getMovieIdByName(String name) throws SQLException {
        return movieManager.getMovieIdByName(name);
    }

    /**
     * Method that uses the MovieManager to create a new movie and adds it
     * to the moviesToBeViewed observable list.
     */
    public void createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception {
        Movie movie = movieManager.createMovie(name, fileLink, personalRating, IMDBRating, lastView);
        moviesToBeViewed.add(movie);
    }

    /**
     * Method that uses the MovieManager to search for movies by a query string,
     * and updates the moviesToBeViewed observable list with the search results.
     */
    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.setAll(searchResults);
    }

    /**
     * Method that uses the MovieManager to update the last view date of a movie
     */
    public void updateDate(int movieId, Date currentDate) throws Exception {
        movieManager.updateDate(movieId, currentDate);
    }
}