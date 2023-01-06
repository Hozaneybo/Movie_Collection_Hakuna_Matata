package gui.model;

import be.Movie;
import bll.MovieManager;
import gui.controller.MovieViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieModel {

    private MovieManager movieManager;
    private ObservableList<Movie> moviesToBeViewed;
    private Movie selectedMovie;

    public Movie getSelectedMovie() {
        return selectedMovie;
    }


    public void setSelectedPlaylist(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public MovieModel() throws SQLException {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        if(movieManager.getAllMovie() != null){
            moviesToBeViewed.addAll(movieManager.getAllMovie());}
        Comparator<Movie> movieComparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(moviesToBeViewed, movieComparator);

    }


    public ObservableList<Movie> getObservableAllMovies()
    {
        return moviesToBeViewed;
    }


    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);
        moviesToBeViewed.remove(movie);
    }



    public void createMovie(String name, String fileLink, double personalRating, double IMDBRating, Date lastView) throws Exception {
        Movie movie = movieManager.createMovie(name, fileLink, personalRating, IMDBRating, lastView);
        moviesToBeViewed.add(movie);
    }
    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }
}
