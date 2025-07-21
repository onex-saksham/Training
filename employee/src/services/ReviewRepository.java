package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.Review;

public class ReviewRepository {
    private static final List<Review> reviews = new ArrayList<>();

    public static void storeReview(Review review) {
        reviews.add(review);
    }

    public static List<Review> findReviewsByEmployeeId(String employeeId) {
        return reviews.stream()
                .filter(r -> r.getEmployeeId().equalsIgnoreCase(employeeId))
                .collect(Collectors.toList());
    }

    public static List<Review> getAllReviews() {
        return new ArrayList<>(reviews);
    }
}
