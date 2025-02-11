package models.books;

public class FANTASY_book extends Book {
    private String genre = "fantasy";

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public FANTASY_book() {}
    public FANTASY_book(String title, String author, String genre) {
    }

    public String toString() {
        return "FANTASY_book";
    }
}
