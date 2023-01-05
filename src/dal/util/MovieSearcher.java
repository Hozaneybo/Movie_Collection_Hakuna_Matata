package dal.util;

import be.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {
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

    private boolean compareToMovieIMDBRating(String query, Movie movie)
    {
        String rating = String.valueOf(movie.getIMDBRating());
        return rating.contains(query);
    }
    
    private boolean compareToMovieName(String query, Movie movie) {
        return movie.getName().toLowerCase().contains(query.toLowerCase());
    }
}
