import models.*;
import services.ReviewService;
import utils.InputValidator;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReviewService reviewService = new ReviewService();

        System.out.println("Enter number of employees:");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            try {
                System.out.println("Enter employee ID:");
                int id = Integer.parseInt(sc.nextLine());

                System.out.println("Enter employee name:");
                String name = sc.nextLine();

                System.out.println("Enter employee position:");
                String position = sc.nextLine();

                System.out.println("Enter employee salary (LPA):");
                double salary = Double.parseDouble(sc.nextLine());

                System.out.println("Enter employee joining date (yyyy-MM-dd):");
                LocalDate doj = InputValidator.parseDate(sc.nextLine());

                Employee emp = new Employee(id, name, position, salary, doj);

                System.out.println("Enter on-time days:");
                int onTime = Integer.parseInt(sc.nextLine());
                System.out.println("Enter late days:");
                int late = Integer.parseInt(sc.nextLine());
                System.out.println("Enter early leave days:");
                int early = Integer.parseInt(sc.nextLine());
                emp.setPunctualityStats(onTime, late, early);

                System.out.println("Enter manager name:");
                String reviewerName = sc.nextLine();
                Reviewer reviewer = new Reviewer(reviewerName, "Manager");

                System.out.println("Enter Manager rating (0–5):");
                double mgrRating = Double.parseDouble(sc.nextLine());
                System.out.println("Enter Colleague rating (0–5):");
                double colRating = Double.parseDouble(sc.nextLine());
                System.out.println("Enter Self rating (0–5):");
                double selfRating = Double.parseDouble(sc.nextLine());

                System.out.println("Enter comments:");
                String comments = sc.nextLine();

                System.out.println("Enter total Jira tickets assigned:");
                int total = Integer.parseInt(sc.nextLine());
                System.out.println("Enter Jira tickets completed on time:");
                int onTimeJira = Integer.parseInt(sc.nextLine());
                System.out.println("Enter Jira tickets missed:");
                int missedJira = Integer.parseInt(sc.nextLine());

                System.out.println("Is the employee under any investigation (true/false):");
                boolean investigation = Boolean.parseBoolean(sc.nextLine());

                Review review = new Review(emp, reviewer, mgrRating, colRating, selfRating, comments, total, onTimeJira, missedJira, investigation);
                reviewService.submitReview(review);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Skipping employee due to input error.\n");
            }
        }

        System.out.println("\n===== All Reviews =====");
        reviewService.printAllReviews();
    }
}
