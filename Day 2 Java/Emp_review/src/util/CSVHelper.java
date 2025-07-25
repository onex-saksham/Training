package util;

import java.io.*;
import java.nio.file.*;
import model.*;

public class CSVHelper {
    private static final String FILE_PATH = "reviews.csv";
    private static final String HEADER = "ID,Name,DOJ,Salary,UnderInvestigation,ManagerRating,ColleagueRating,PersonalRating,Closed,Open,Timely,OnTime,EarlyLeaves,FinalScore,Increment%";

    public static void writeToCSV(Employee emp, Review review, JiraPerformance jira, Attendance attendance, double score, double increment) {
        boolean fileExists = Files.exists(Path.of(FILE_PATH));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (!fileExists) {
                writer.write(HEADER);
                writer.newLine();
            }

            String row = String.join(",",
                emp.getId(),
                emp.getName(),
                emp.getDateOfJoining().toString(),
                String.valueOf(emp.getSalaryLPA()),
                String.valueOf(emp.isUnderInvestigation()),
                String.valueOf(review.getManagerRating()),
                String.valueOf(review.getColleagueRating()),
                String.valueOf(review.getPersonalRating()),
                String.valueOf(jira.getClosedTickets()),
                String.valueOf(jira.getOpenTickets()),
                String.valueOf(jira.getTimelyCompleted()),
                String.valueOf(attendance.getDaysOnTime()),
                String.valueOf(attendance.getEarlyLeaves()),
                String.format("%.2f", score),
                String.format("%.2f", increment)
            );

            writer.write(row);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    public static void readCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;
            System.out.println("\n========= EMPLOYEE REVIEW REPORT =========");

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    System.out.println("\n--- Columns ---");
                    String[] headers = line.split(",");
                    for (int i = 0; i < headers.length; i++) {
                        System.out.printf("%-20s", headers[i]);
                    }
                    System.out.println("\n------------------------------------------------------------");
                    firstLine = false;
                } else {
                    String[] values = line.split(",");
                    for (String value : values) {
                        System.out.printf("%-20s", value);
                    }
                    System.out.println();
                }
            }
            System.out.println("==========================================\n");
        } catch (IOException e) {
            System.err.println("Error reading from CSV: " + e.getMessage());
        }
    }
}  
