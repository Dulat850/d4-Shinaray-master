package models.books;

public class FANTASY_book extends Book implements book_genre{
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

    @Override
    public String genre() {

        return getGenre();
    }
}
