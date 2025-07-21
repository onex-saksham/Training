package com.company.review.model;

public class PerformanceMetrics {
    private int jiraClosed;
    private int jiraUnclosed;
    private boolean underInvestigation;
    private int onTimeDays;
    private int earlyLeaves;
    private int timelySubmissions;

    public PerformanceMetrics(int closed, int unclosed, boolean underInvestigation,
                              int onTimeDays, int earlyLeaves, int timelySubmissions) {
        this.jiraClosed = closed;
        this.jiraUnclosed = unclosed;
        this.underInvestigation = underInvestigation;
        this.onTimeDays = onTimeDays;
        this.earlyLeaves = earlyLeaves;
        this.timelySubmissions = timelySubmissions;
    }

    public int getJiraClosed() { return jiraClosed; }
    public int getJiraUnclosed() { return jiraUnclosed; }
    public boolean isUnderInvestigation() { return underInvestigation; }
    public int getOnTimeDays() { return onTimeDays; }
    public int getEarlyLeaves() { return earlyLeaves; }
    public int getTimelySubmissions() { return timelySubmissions; }
}
