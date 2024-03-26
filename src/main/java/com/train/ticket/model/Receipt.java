package com.train.ticket.model;


public class Receipt {
    private String from;
    private String to;
    private User user;
    private double pricePaid;
    private Seat userSeat; // Added userSeat property


    public Receipt(String from, String to, User user, double pricePaid) {
        this.from = from;
        this.to = to;
        this.user = user;
        this.pricePaid = pricePaid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public Seat getUserSeat() {
        return userSeat;
    }

    public void setUserSeat(Seat userSeat) {
        this.userSeat = userSeat;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }
}
