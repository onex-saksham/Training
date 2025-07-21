package com.company.review.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Employee {
    private String name;
    private String id;
    private double salaryLPA;
    private LocalDate dateOfJoining;

    public Employee(String name, String id, double salaryLPA, LocalDate dateOfJoining) {
        this.name = name;
        this.id = id;
        this.salaryLPA = salaryLPA;
        this.dateOfJoining = dateOfJoining;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public double getSalaryLPA() { return salaryLPA; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }

    public long getTenureInMonths() {
        return ChronoUnit.MONTHS.between(dateOfJoining, LocalDate.now());
    }

    public boolean isEligibleForReview() {
        return getTenureInMonths() >= 12;
    }
}
