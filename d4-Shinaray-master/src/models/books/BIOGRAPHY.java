package models.books;

public class BIOGRAPHY extends Book {
    private String genre = "BIOGRAPHY";

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BIOGRAPHY() {
        super();
    }

    public String toString() {
        return "BIOGRAPHY";
    }
}
