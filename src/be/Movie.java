package be;

import java.util.Date;

public class Movie {
    private int id;
    private String name, filelink;
    private Double rating;
    private Date lastview;

    public Movie(int id, String name, Double rating, String filelink, Date lastview){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.filelink = filelink;
        this.lastview = lastview;
    }

    public Movie(){
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public Date getLastview() {
        return lastview;
    }

    public void setLastview(Date lastview) {
        this.lastview = lastview;
    }
}
