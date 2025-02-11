package models.review_r;

public class negative_r extends Review {
    private Boolean rating = false;

    public negative_r() {
        super();
    }

    public Boolean getr() {
        return rating;
    }

    public String toString(){
        return "Rating: " + rating;
    }
}
