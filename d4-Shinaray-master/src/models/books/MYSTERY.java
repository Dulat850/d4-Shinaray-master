package models.books;

public class MYSTERY extends Book {
    private String genre = "MYSTERY";

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MYSTERY(String genre) {
        this.genre = genre;
    }

    public MYSTERY() {
        super();
    }

    public String toString() {
        return "MYSTERY [genre=" + genre + "]";
    }
}
