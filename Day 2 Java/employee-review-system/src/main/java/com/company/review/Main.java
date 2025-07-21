package com.company.review;

import com.company.review.model.*;
import com.company.review.service.ReviewService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee("John Doe", "EMP101", 14.5, LocalDate.of(2022, 7, 15));
        PerformanceMetrics metrics = new PerformanceMetrics(30, 3, false, 18, 2, 25);
        ReviewScores scores = new ReviewScores(4.5, 4.2, 4.3);

        ReviewService service = new ReviewService();
        String output = service.evaluate(emp, metrics, scores);
        System.out.println(output);
    }
}
