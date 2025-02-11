package models.review_r;

public class positive_review extends Review {
    private Boolean rating = true;

    public positive_review() {
        super();
    }

    public Boolean getr() {
        return rating;
    }

    public String toString(){
        return "Rating: " + rating;
    }
}
