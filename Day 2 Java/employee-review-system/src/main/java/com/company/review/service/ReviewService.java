package com.company.review.service;

import com.company.review.model.Employee;
import com.company.review.model.PerformanceMetrics;
import com.company.review.model.ReviewScores;
import com.company.review.util.ScoreUtil;

public class ReviewService {

    public String evaluate(Employee employee, PerformanceMetrics metrics, ReviewScores scores) {
        if (!employee.isEligibleForReview()) {
            return "Employee " + employee.getName() + " has not completed 1 year. Not eligible for review.";
        }

        double performanceScore = ScoreUtil.calculatePerformanceScore(metrics, scores);
        double increment = ScoreUtil.calculateIncrement(employee.getSalaryLPA(), performanceScore);
        double avgReviewScore = scores.getAverage();

        StringBuilder sb = new StringBuilder();
        sb.append("Employee Review for ").append(employee.getName()).append("\n")
          .append("ID: ").append(employee.getId()).append("\n")
          .append("Average Review Score: ").append(String.format("%.2f", avgReviewScore)).append("\n")
          .append("Performance Score: ").append(String.format("%.2f", performanceScore)).append("\n");

        if (performanceScore < 20) {
            sb.append("Final Verdict: Negative performance. No increment suggested.\n");
        } else {
            sb.append("Final Verdict: Positive performance.\n")
              .append("Suggested Increment: ").append(String.format("%.2f", increment)).append(" %\n");
        }

        return sb.toString();
    }
}
