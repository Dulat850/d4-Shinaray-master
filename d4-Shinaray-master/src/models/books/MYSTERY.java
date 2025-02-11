package models.books;

public class MYSTERY extends Book implements book_genre{
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

    @Override
    public String genre() {
        return getGenre();
    }
}
