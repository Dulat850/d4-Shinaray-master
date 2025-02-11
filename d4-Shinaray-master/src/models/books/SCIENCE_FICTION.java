package models.books;

public class SCIENCE_FICTION extends Book implements book_genre{
    private String genre = "SCIENCE_FICTION";

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public SCIENCE_FICTION() {}

    public SCIENCE_FICTION(String title, String author, String genre) {
        super();
    }

    public String toString() {
        return "SCIENCE_FICTION";
    }

    @Override
    public String genre() {
        return getGenre();
    }
}
