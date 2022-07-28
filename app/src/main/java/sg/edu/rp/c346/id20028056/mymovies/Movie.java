package sg.edu.rp.c346.id20028056.mymovies;
import java.io.Serializable;


public class Movie implements Serializable {
    private int id,year;
    private String title, genre,rating;

    public Movie(int id,String title, String genre,int year, String rating) {
        this.id=id;
        this.title = title;
        this.genre =genre;
        this.rating=rating;
        this.year=year;
    }

    public int get_id() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void updateDetails(String title,String genre, int year, String rating)
    {
        this.id=id;
        this.title = title;
        this.genre =genre;
        this.rating=rating;
        this.year=year;
    }



    public String toString() { return "ID: " + id + ",\nTitle: " + title +",\nGenre: "+ genre +",\nYear: "+ year+
            " ,\nRating: "+rating;  }

}
