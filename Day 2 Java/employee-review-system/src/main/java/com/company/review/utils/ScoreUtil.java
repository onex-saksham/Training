package com.company.review.util;

import com.company.review.model.PerformanceMetrics;
import com.company.review.model.ReviewScores;

public class ScoreUtil {

    public static double calculatePerformanceScore(PerformanceMetrics metrics, ReviewScores scores) {
        double score = 0;

        score += metrics.getJiraClosed() * 1.5;
        score -= metrics.getJiraUnclosed() * 2;
        score += metrics.getOnTimeDays() * 0.5;
        score -= metrics.getEarlyLeaves() * 0.5;
        score += metrics.getTimelySubmissions() * 1;
        score += scores.getAverage() * 5;

        if (metrics.isUnderInvestigation()) {
            score -= 10;
        }

        return score;
    }

    public static double calculateIncrement(double salaryLPA, double score) {
        if (score < 20) return 0;

        double max = salaryLPA < 15 ? 15 : 7;
        double min = salaryLPA < 15 ? 5 : 0;

        double cappedScore = Math.min(score, 50);
        double normalized = (cappedScore - 20) / 30;

        return min + normalized * (max - min);
    }
}
