package com.train.ticket.model;

import java.util.Objects;

public class Seat {
    private int seatNumber;
    private String section;

    public Seat(int seatNumber, String section) {
        if (seatNumber <= 0) {
            throw new IllegalArgumentException("Seat number cannot be negative");
        }
        this.seatNumber = seatNumber;
        this.section = section;
    }
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Seat otherSeat = (Seat) obj;
        return seatNumber == otherSeat.seatNumber &&
                Objects.equals(section, otherSeat.section);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatNumber=" + seatNumber +
                ", section='" + section + '\'' +
                '}';
    }
}
