package model;

public class Attendance {
    private int daysOnTime;
    private int earlyLeaves;

    public Attendance(int daysOnTime, int earlyLeaves) {
        this.daysOnTime = daysOnTime;
        this.earlyLeaves = earlyLeaves;
    }

    public int getDaysOnTime() { return daysOnTime; }
    public int getEarlyLeaves() { return earlyLeaves; }
}
