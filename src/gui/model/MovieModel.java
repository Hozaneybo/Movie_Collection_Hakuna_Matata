package gui.model;

import be.Movie;
import bll.MovieManager;
import gui.controller.MovieViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;

public class MovieModel {

    private MovieManager movieManager;
    private ObservableList<Movie> moviesToBeViewed;
    private Movie selectedMovie;

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    /**
     * Setter for the  selected playlist
     */
    public void setSelectedPlaylist(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    /**
     * Creates a new AllPLaylistsManager to be able to use the methods from the AllPlaylistsManager class
     *
     * @throws SQLException
     */
    public MovieModel() throws SQLException {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        if(movieManager.getAllMovie() != null)
            moviesToBeViewed.addAll(movieManager.getAllMovie());

    }

    /**
     * Creates a List with all songs from the allPlaylistsToBeViewed method
     */
    public ObservableList<Movie> getObservableAllMovies()
    {
        return moviesToBeViewed;
    }

    /**
     * Gets value of deletedPlaylist from the allPlaylistsManager class
     * Remove value from allPlaylistsToBeViewed
     * @throws Exception
     */
    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);
        moviesToBeViewed.remove(movie);
    }


    /**
     * Get the name value from createNewPlaylist method in the allPlaylistsManager class
     * @throws Exception
     */
    public void createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception {
        Movie movie = movieManager.createMovie(name, fileLink, personalRating, IMDBRating, lastView);
        moviesToBeViewed.add(movie);
    }

}
