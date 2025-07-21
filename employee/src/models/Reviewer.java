package models;

public class Reviewer {
    private String reviewerId;
    private String role;

    public Reviewer(String reviewerId, String role) {
        this.reviewerId = reviewerId;
        this.role = role;
    }

    public String getReviewerId() { return reviewerId; }
    public String getRole() { return role; }
}
