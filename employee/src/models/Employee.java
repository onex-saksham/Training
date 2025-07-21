package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Employee {
    private int id;
    private String name;
    private String position;
    private double salaryInLpa;
    private LocalDate dateOfJoining;
    private int onTimeDays;
    private int lateDays;
    private int earlyLeaves;

    public Employee(int id, String name, String position, double salaryInLpa, LocalDate dateOfJoining) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Employee name cannot be empty.");
        if (position == null || position.isEmpty()) throw new IllegalArgumentException("Position cannot be empty.");
        if (salaryInLpa <= 0) throw new IllegalArgumentException("Salary must be greater than 0.");
        if (dateOfJoining == null || dateOfJoining.isAfter(LocalDate.now())) throw new IllegalArgumentException("Invalid joining date.");

        this.id = id;
        this.name = name;
        this.position = position;
        this.salaryInLpa = salaryInLpa;
        this.dateOfJoining = dateOfJoining;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalaryInLpa() { return salaryInLpa; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public int getOnTimeDays() { return onTimeDays; }
    public int getLateDays() { return lateDays; }
    public int getEarlyLeaves() { return earlyLeaves; }

    public void setPunctualityStats(int onTimeDays, int lateDays, int earlyLeaves) {
        long totalWorkingDays = ChronoUnit.DAYS.between(dateOfJoining, LocalDate.now());
        int total = onTimeDays + lateDays + earlyLeaves;
        if (total > totalWorkingDays) throw new IllegalArgumentException("Punctuality stats exceed total working days.");
        if (onTimeDays < 0 || lateDays < 0 || earlyLeaves < 0)
            throw new IllegalArgumentException("Punctuality stats must be non-negative.");

        this.onTimeDays = onTimeDays;
        this.lateDays = lateDays;
        this.earlyLeaves = earlyLeaves;
    }
}