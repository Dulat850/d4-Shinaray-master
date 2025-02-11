package controllers;

import controllers.interfaces.IReviewController;
import models.Review;
import repositories.interfaces.IReviewRepository;
import java.util.List;

public class ReviewController implements IReviewController {
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addReview(int userId, String comment, int rating) {
        Review review = new Review(userId, comment, rating);
        boolean created = repo.addReview(review);
        return (created) ? "Review added successfully" : "Failed to add review";
    }

    @Override
    public String getReviewsByUser(int userId) {
        List<Review> reviews = repo.getReviewsByUser(userId);
        if (reviews == null || reviews.isEmpty()) {
            return "No reviews found for user ID: " + userId;
        }

        StringBuilder response = new StringBuilder("Reviews for user " + userId + ":\n");
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }
}
