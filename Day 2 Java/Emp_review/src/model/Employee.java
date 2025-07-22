package model;

import java.time.LocalDate;

public class Employee {
    private String id;
    private String name;
    private LocalDate dateOfJoining;
    private double salaryLPA;
    private boolean underInvestigation;

    public Employee(String id, String name, LocalDate dateOfJoining, double salaryLPA, boolean underInvestigation) {
        this.id = id;
        this.name = name;
        this.dateOfJoining = dateOfJoining;
        this.salaryLPA = salaryLPA;
        this.underInvestigation = underInvestigation;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public double getSalaryLPA() { return salaryLPA; }
    public boolean isUnderInvestigation() { return underInvestigation; }
}
