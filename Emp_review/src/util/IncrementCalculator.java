package util;

public class IncrementCalculator {
    public static double calculateIncrement(double performanceScore, double salaryLPA) {
        if (performanceScore < 5) return 0.0;

        if (salaryLPA < 15.0) {
            return 5.0 + (performanceScore - 5.0) * 2; // 5% to 15%
        } else {
            return (performanceScore - 5.0) * 1.4; // 0% to 7%
        }
    }
}
