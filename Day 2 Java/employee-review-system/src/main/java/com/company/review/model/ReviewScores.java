package com.company.review.model;

public class ReviewScores {
    private double managerRating;
    private double colleagueRating;
    private double selfRating;

    public ReviewScores(double managerRating, double colleagueRating, double selfRating) {
        this.managerRating = managerRating;
        this.colleagueRating = colleagueRating;
        this.selfRating = selfRating;
    }

    public double getAverage() {
        return (managerRating + colleagueRating + selfRating) / 3.0;
    }

    public double getManagerRating() { return managerRating; }
    public double getColleagueRating() { return colleagueRating; }
    public double getSelfRating() { return selfRating; }
}
