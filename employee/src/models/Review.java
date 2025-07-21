package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Review {
    private static List<Review> storedReviews = new ArrayList<>();

    private Employee employee;
    private Reviewer reviewer;
    private double managerRating;
    private double colleagueRating;
    private double selfRating;
    private String comments;
    private int totalJira;
    private int jiraOnTime;
    private int jiraMissed;
    private boolean underInvestigation;

    public Review(Employee employee, Reviewer reviewer, double managerRating, double colleagueRating, double selfRating, String comments, int totalJira, int jiraOnTime, int jiraMissed, boolean underInvestigation) {
        if (employee == null || reviewer == null) throw new IllegalArgumentException("Employee and reviewer cannot be null.");
        if (managerRating < 0 || managerRating > 5 || colleagueRating < 0 || colleagueRating > 5 || selfRating < 0 || selfRating > 5)
            throw new IllegalArgumentException("Ratings must be between 0 and 5.");
        if (comments == null) comments = "";
        if (totalJira < 0 || jiraOnTime < 0 || jiraMissed < 0)
            throw new IllegalArgumentException("Jira stats must be non-negative.");
        if (jiraOnTime + jiraMissed > totalJira)
            throw new IllegalArgumentException("Sum of on-time and missed Jira tickets cannot exceed total Jira tickets.");

        this.employee = employee;
        this.reviewer = reviewer;
        this.managerRating = managerRating;
        this.colleagueRating = colleagueRating;
        this.selfRating = selfRating;
        this.comments = comments;
        this.totalJira = totalJira;
        this.jiraOnTime = jiraOnTime;
        this.jiraMissed = jiraMissed;
        this.underInvestigation = underInvestigation;

        storedReviews.add(this);
    }

    public static List<Review> getAllStoredReviews() {
        return storedReviews;
    }

    public boolean isEligibleForReview() {
        return ChronoUnit.YEARS.between(employee.getDateOfJoining(), LocalDate.now()) >= 1;
    }

    public double calculateFinalScore() {
        if (!isEligibleForReview()) return 0;

        double averageRating = (managerRating + colleagueRating + selfRating) / 3;
        double punctualityScore = Math.min(1, employee.getOnTimeDays() * 0.1) - (employee.getLateDays() * 0.2) - (employee.getEarlyLeaves() * 0.2);
        double jiraScore = (jiraOnTime * 0.2) - (jiraMissed * 0.3);
        double penalty = underInvestigation ? 1 : 0;

        return Math.max(0, Math.min(5, averageRating + punctualityScore + jiraScore - penalty));
    }

    public double calculateSuggestedIncrement() {
        if (!isEligibleForReview()) return 0;

        double finalScore = calculateFinalScore();
        double salary = employee.getSalaryInLpa();

        if (salary < 15) {
            if (finalScore >= 3.5) return 10 + Math.random() * 5;      // 10-15%
            else if (finalScore >= 2) return 5 + Math.random() * 5;   // 5-10%
            else return 0;
        } else {
            if (finalScore >= 3.5) return 5 + Math.random() * 2;       // 5-7%
            else if (finalScore >= ) return 2 + Math.random() * 3;   // 2-5%
            else return 0;
        }
    }

    public String toString() {
        if (!isEligibleForReview()) {
            return "Employee " + employee.getName() + " is not eligible for review (less than 1 year).";
        }

        return String.format(
            "Review for %s (%s):\nManager Rating: %.2f\nColleague Rating: %.2f\nSelf Rating: %.2f\nFinal Score: %.2f\nSuggested Increment: %.2f%%\nComments: %s",
            employee.getName(), employee.getPosition(),
            managerRating, colleagueRating, selfRating,
            calculateFinalScore(), calculateSuggestedIncrement(),
            comments
        );
    }
}
