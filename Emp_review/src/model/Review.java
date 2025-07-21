package model;

public class Review {
    private double managerRating;
    private double colleagueRating;
    private double personalRating;

    public Review(double managerRating, double colleagueRating, double personalRating) {
        this.managerRating = managerRating;
        this.colleagueRating = colleagueRating;
        this.personalRating = personalRating;
    }

    public double average() {
        return (managerRating + colleagueRating + personalRating) / 3.0;
    }

    // ✅ Add these getters to fix the compilation error
    public double getManagerRating() {
        return managerRating;
    }

    public double getColleagueRating() {
        return colleagueRating;
    }

    public double getPersonalRating() {
        return personalRating;
    }
}
