package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
        }
    }
}
