import java.time.LocalDate;
import model.*;
import service.ReviewService;
import util.CSVHelper;
import util.InputValidator;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========= EMPLOYEE REVIEW SYSTEM =========");
            System.out.println("1. Evaluate an Employee");
            System.out.println("2. Show All Reviews from CSV");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = InputValidator.getInt("", 1); // Min 1
            switch (choice) {
                case 1 -> evaluateEmployee();
                case 2 -> CSVHelper.readCSV();
                case 3 -> {
                    System.out.println("Exiting. Thank you!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void evaluateEmployee() {
        System.out.println("\n--- Enter Employee Details ---");
        String id = InputValidator.getString("Enter Employee ID: ");
        String name = InputValidator.getString("Enter Employee Name: ");
        LocalDate doj = InputValidator.getDate("Enter Date of Joining (yyyy-MM-dd): ");
        double salary = InputValidator.getDouble("Enter Current Salary (LPA): ", 0, 100); // reasonable cap
        boolean underInvestigation = InputValidator.getBoolean("Is the employee under investigation");

        System.out.println("\n--- Enter Review Ratings (0–5) ---");
        double managerRating = InputValidator.getDouble("Manager Review: ", 0, 5);
        double colleagueRating = InputValidator.getDouble("Colleague Review: ", 0, 5);
        double personalRating = InputValidator.getDouble("Self Review: ", 0, 5);

        System.out.println("\n--- Enter Jira Performance ---");
        int closedTickets = InputValidator.getInt("Tickets closed: ", 0);
        int openTickets = InputValidator.getInt("Tickets still open: ", 0);
        int timelyCompleted = InputValidator.getInt("Tickets completed on time: ", 0);

        System.out.println("\n--- Enter Attendance Details ---");
        int daysOnTime = InputValidator.getInt("Days on time this month: ", 0);
        int earlyLeaves = InputValidator.getInt("Number of early leaves: ", 0);

        // Create objects
        Employee emp = new Employee(id, name, doj, salary, underInvestigation);
        Review review = new Review(managerRating, colleagueRating, personalRating);
        JiraPerformance jira = new JiraPerformance(closedTickets, openTickets, timelyCompleted);
        Attendance attendance = new Attendance(daysOnTime, earlyLeaves);

        // Review employee
        System.out.println("\n------ REVIEW RESULT ------");
        ReviewService.reviewEmployee(emp, review, jira, attendance);
    }
}
