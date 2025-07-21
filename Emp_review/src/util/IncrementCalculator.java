package util;

public class IncrementCalculator {

    public static double calculateIncrement(double score, double salaryLPA) {
        if (score <= 5) return 0.0;

        if (salaryLPA < 15.0) {
            if (score <= 6) return 6.0;
            else if (score <= 7) return 8.0;
            else if (score <= 8) return 11.0;
            else if (score <= 9) return 13.0;
            else return 15.0;
        } else {
            if (score <= 6) return 1.0;
            else if (score <= 7) return 2.5;
            else if (score <= 8) return 4.5;
            else if (score <= 9) return 6.0;
            else return 7.0;
        }
    }
}
