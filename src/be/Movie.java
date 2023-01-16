package be;

import java.util.Date;


public class Movie {
    // ID of the movie
    private int id;
    // Name of the movie
    private String name, fileLink;
    // Personal rating of the movie
    private double personalRating;
    // IMDB rating of the movie
    private double IMDBRating;
    // Last view date of the movie
    private Date lastView;

    // Constructor to create a new movie
    public Movie(int id, String name, String fileLink, double personalRating, double IMDBRating, Date lastView) {
        this.id = id;
        this.name = name;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.personalRating = personalRating;
        this.IMDBRating = IMDBRating;
    }

    // Method to get the ID of the movie
    public int getId() {
        return id;
    }

    // Method to set the ID of the movie
    public void setId(int id) {
        this.id = id;
    }

    // Method to get the name of the movie
    public String getName() {
        return name;
    }

    // Method to set the name of the movie
    public void setName(String name) {
        this.name = name;
    }

    // Method to get the file link of the movie
    public String getFileLink() {
        return fileLink;
    }

    // Method to set the file link of the movie
    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    // Method to get the personal rating of the movie
    public double getPersonalRating() {
        return personalRating;
    }

    // Method to set the personal rating of the movie
    public void setPersonalRating(double personalRating) {
        this.personalRating = personalRating;
    }

    // Method to get the IMDB rating of the movie
    public double getIMDBRating() {
        return IMDBRating;
    }

    // Method to set the IMDB rating of the movie
    public void setIMDBRating(double IMDBRating) {
        this.IMDBRating = IMDBRating;
    }

    // Method to get the last view date of the movie
    public Date getLastView() {
        return lastView;
    }

    // Method to set the last view date of the movie
    public void setLastView(Date lastView) {
        this.lastView = lastView;
    }
}