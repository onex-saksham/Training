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

        // Normalize Jira metrics by months worked
        double closedTicketsPerMonth = (double) jira.getClosedTickets() / tenureMonths;
        double openTicketsPerMonth = (double) jira.getOpenTickets() / tenureMonths;
        double timelyCompletedPerMonth = (double) jira.getTimelyCompleted() / tenureMonths;

        double score = review.average();
        score += closedTicketsPerMonth * 0.5;
        score -= openTicketsPerMonth * 0.3;
        score += timelyCompletedPerMonth * 0.4;
        score += attendance.getDaysOnTime() * 0.05;
        score -= attendance.getEarlyLeaves() * 0.1;

        if (emp.isUnderInvestigation()) {
            score -= 2.0;
        }

        score = Math.max(0, Math.min(10, score)); // Clamp score between 0 and 10

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
