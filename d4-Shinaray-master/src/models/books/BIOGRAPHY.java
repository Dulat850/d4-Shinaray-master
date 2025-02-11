package models.books;

public class BIOGRAPHY extends Book implements book_genre {
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

    @Override
    public String genre() {

        return getGenre();
    }
}
