package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("❌ Input cannot be empty. Try again.");
        }
    }

    public static LocalDate getDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Use yyyy-MM-dd.");
            }
        }
    }

    public static double getDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
                System.out.printf("❌ Value must be between %.1f and %.1f\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }

    public static int getInt(String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min) return value;
                System.out.printf("❌ Value must be at least %d\n", min);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid integer. Try again.");
            }
        }
    }

    public static boolean getBoolean(String prompt) {
        while (true) {
            System.out.print(prompt + " (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) return true;
            if (input.equals("false")) return false;
            System.out.println("❌ Enter either 'true' or 'false'.");
        }
    }
    public static boolean validateName(String name) {
        return name.matches("[A-Za-z ]+");
    }

    public static LocalDate validateDate(String input) {
        try {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static double validateDouble(String input, double min, double max) {
        try {
            double val = Double.parseDouble(input);
            if (val < min || val > max) throw new NumberFormatException();
            return val;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            return -1;
        }
    }

    public static int validateInteger(String input, int min, int max) {
        try {
            int val = Integer.parseInt(input);
            if (val < min || val > max) throw new NumberFormatException();
            return val;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer between " + min + " and " + max + ".");
            return -1;
        }
    }

    public static boolean validateBoolean(String input) {
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(input);
        } else {
            throw new IllegalArgumentException("Invalid input. Enter 'true' or 'false'.");
        }
    }
}
