package be;


import java.util.Date;

public class Movie {
    private int id;
    private String name, fileLink;
    private double personalRating, IMDBRating;
    private Date lastView;

    public Movie(int id, String name, String fileLink, double personalRating, double IMDBRating, Date lastView){
        this.id = id;
        this.name = name;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.personalRating = personalRating;
        this.IMDBRating = IMDBRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public double getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(double personalRating) {
        this.personalRating = personalRating;
    }

    public double getIMDBRating() {
        return IMDBRating;
    }

    public void setIMDBRating(double IMDBRating) {
        this.IMDBRating = IMDBRating;
    }

    public Date getLastView() {
        return lastView;
    }

    public void setLastView(Date lastView) {
        this.lastView = lastView;
    }
}
