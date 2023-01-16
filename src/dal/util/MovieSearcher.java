package dal.util;


import be.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {
    /**
     * Search a list of movies based on the given query
     * @param searchBase List of movies to search
     * @param query Search query
     * @return List of movies that match the search query
     */
    public List<Movie> search(List<Movie> searchBase, String query) {
        List<Movie> searchResult = new ArrayList<>();
        for (Movie movie : searchBase) {
            if(compareToMovieName(query, movie)  || compareToMovieIMDBRating(query, movie))
            {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    /**
     * Compare a movie's IMDB rating to the given query
     * @param query Search query
     * @param movie Movie to compare
     * @return true if the movie's IMDB rating matches the query, false otherwise
     */
    private boolean compareToMovieIMDBRating(String query, Movie movie)
    {
        String rating = String.valueOf(movie.getIMDBRating());
        return rating.contains(query);
    }

    /**
     * Compare a movie's name to the given query
     * @param query Search query
     * @param movie Movie to compare
     * @return true if the movie's name matches the query, false otherwise
     */
    private boolean compareToMovieName(String query, Movie movie) {
        return movie.getName().toLowerCase().contains(query.toLowerCase());
    }

}