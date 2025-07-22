package model;

public class JiraPerformance {
    private int closedTickets;
    private int openTickets;
    private int timelyCompleted;

    public JiraPerformance(int closedTickets, int openTickets, int timelyCompleted) {
        this.closedTickets = closedTickets;
        this.openTickets = openTickets;
        this.timelyCompleted = timelyCompleted;
    }

    public int getClosedTickets() { return closedTickets; }
    public int getOpenTickets() { return openTickets; }
    public int getTimelyCompleted() { return timelyCompleted; }
}
