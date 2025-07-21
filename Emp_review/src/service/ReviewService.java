package service;

import model.*;
import util.IncrementCalculator;
import util.CSVHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReviewService {

    public static void reviewEmployee(Employee emp, Review review, JiraPerformance jira, Attendance attendance) {
        long tenureMonths = ChronoUnit.MONTHS.between(emp.getDateOfJoining(), LocalDate.now());

        System.out.println("Review for " + emp.getName() + ":");

        if (tenureMonths < 12) {
            System.out.println("Cannot evaluate. Tenure less than 1 year.");
            return;
        }

        double score = review.average();
        score += jira.getClosedTickets() * 0.2;
        score -= jira.getOpenTickets() * 0.2;
        score += jira.getTimelyCompleted() * 0.1;
        score += attendance.getDaysOnTime() * 0.05;
        score -= attendance.getEarlyLeaves() * 0.1;

        if (emp.isUnderInvestigation()) {
            score -= 2.0;
        }

        score = Math.max(0, Math.min(10, score)); // Clamp

        double increment = IncrementCalculator.calculateIncrement(score, emp.getSalaryLPA());

        System.out.printf("Final Score: %.2f/10\n", score);
        if (increment > 0) {
            System.out.printf("Suggested Increment: %.2f%%\n", increment);
        } else {
            System.out.println("Performance below expectations. No increment recommended.");
        }

        CSVHelper.writeToCSV(emp, review, jira, attendance, score, increment);
    }
}
