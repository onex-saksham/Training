import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.*;
import service.ReviewService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========= EMPLOYEE REVIEW SYSTEM =========");
            System.out.println("1. Evaluate an Employee");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear newline

            switch (choice) {
                case 1 -> evaluateEmployee();
                case 2 -> {
                    System.out.println("Exiting. Thank you!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void evaluateEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Date of Joining (yyyy-MM-dd): ");
        LocalDate doj = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Enter Current Salary (LPA): ");
        double salary = scanner.nextDouble();

        System.out.print("Is the employee under investigation (true/false)? ");
        boolean underInvestigation = scanner.nextBoolean();

        System.out.print("Manager Review (0-5): ");
        double managerRating = scanner.nextDouble();

        System.out.print("Colleague Review (0-5): ");
        double colleagueRating = scanner.nextDouble();

        System.out.print("Self Review (0-5): ");
        double personalRating = scanner.nextDouble();

        System.out.print("Number of Jira tickets closed: ");
        int closedTickets = scanner.nextInt();

        System.out.print("Number of Jira tickets still open: ");
        int openTickets = scanner.nextInt();

        System.out.print("Number of Jira tickets completed on time: ");
        int timelyCompleted = scanner.nextInt();

        System.out.print("Days employee was on time this month: ");
        int daysOnTime = scanner.nextInt();

        System.out.print("Number of early leaves this month: ");
        int earlyLeaves = scanner.nextInt();

        // Create objects
        Employee emp = new Employee(id, name, doj, salary, underInvestigation);
        Review review = new Review(managerRating, colleagueRating, personalRating);
        JiraPerformance jira = new JiraPerformance(closedTickets, openTickets, timelyCompleted);
        Attendance attendance = new Attendance(daysOnTime, earlyLeaves);

        // Evaluate
        System.out.println("\n------ REVIEW RESULT ------");
        ReviewService.reviewEmployee(emp, review, jira, attendance);
    }
}
