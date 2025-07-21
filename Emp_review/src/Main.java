import java.time.LocalDate;
import java.util.Scanner;
import model.*;
import service.ReviewService;
import util.CSVHelper;
import util.InputValidator;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("========= EMPLOYEE REVIEW SYSTEM =========");
            System.out.println("1. Evaluate an Employee");
            System.out.println("2. Show All Reviews from CSV");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice = InputValidator.validateInteger(input, 1, 3);
            if (choice == -1) continue;

            switch (choice) {
                case 1:
                    evaluateEmployee();
                    break;
                case 2:
                    CSVHelper.readCSV();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private static void evaluateEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            String id = scanner.nextLine().trim();
            if (id.isEmpty()) throw new IllegalArgumentException("ID cannot be empty");

            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine().trim();
            if (!InputValidator.validateName(name)) throw new IllegalArgumentException("Invalid name (only letters and spaces allowed)");

            System.out.print("Enter Date of Joining (yyyy-MM-dd): ");
            String dojInput = scanner.nextLine().trim();
            LocalDate doj = InputValidator.validateDate(dojInput);
            if (doj == null) throw new IllegalArgumentException("Invalid date format");

            System.out.print("Enter Current Salary (LPA): ");
            double salary = InputValidator.validateDouble(scanner.nextLine(), 0, 100);
            if (salary <= 0) throw new IllegalArgumentException("Invalid salary");

            System.out.print("Is the employee under investigation (true/false)? ");
            boolean underInvestigation = InputValidator.validateBoolean(scanner.nextLine());

            System.out.print("Manager Review (0-5): ");
            double managerRating = InputValidator.validateDouble(scanner.nextLine(), 0, 5);
            System.out.print("Colleague Review (0-5): ");
            double colleagueRating = InputValidator.validateDouble(scanner.nextLine(), 0, 5);
            System.out.print("Self Review (0-5): ");
            double personalRating = InputValidator.validateDouble(scanner.nextLine(), 0, 5);

            System.out.print("Number of Jira tickets closed: ");
            int closed = InputValidator.validateInteger(scanner.nextLine(), 0, 10000);
            System.out.print("Number of Jira tickets still open: ");
            int open = InputValidator.validateInteger(scanner.nextLine(), 0, 10000);
            System.out.print("Number of Jira tickets completed on time: ");
            int timely = InputValidator.validateInteger(scanner.nextLine(), 0, closed);

            System.out.print("Days employee was on time this month: ");
            int onTime = InputValidator.validateInteger(scanner.nextLine(), 0, 31);
            System.out.print("Number of early leaves this month: ");
            int earlyLeaves = InputValidator.validateInteger(scanner.nextLine(), 0, 31);

            Employee employee = new Employee(id, name, doj, salary, underInvestigation);
            Review review = new Review(managerRating, colleagueRating, personalRating);
            JiraPerformance jira = new JiraPerformance(closed, open, timely);
            Attendance attendance = new Attendance(onTime, earlyLeaves);

            ReviewService.reviewEmployee(employee, review, jira, attendance);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}