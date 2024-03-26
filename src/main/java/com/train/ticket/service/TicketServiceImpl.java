package com.train.ticket.service;

import com.train.ticket.model.Receipt;
import com.train.ticket.model.Response;
import com.train.ticket.model.Seat;
import com.train.ticket.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {
    private Map<String, Seat> sectionA = new HashMap<>();
    private Map<String, Seat> sectionB = new HashMap<>();
    private double ticketPrice = 20.0;
    private Map<String, Receipt> receiptsByUserEmail = new HashMap<>();

    public ResponseEntity<Receipt> purchaseTicket(Receipt receipt) {
        validateUser(receipt.getUser());

        Seat seat = assignSeat(receipt.getUser());
        receipt.setUserSeat(seat);

        receipt.setPricePaid(ticketPrice);
        receiptsByUserEmail.put(receipt.getUser().getEmail(), receipt);
        return  ResponseEntity.ok(receipt);
    }

    private void validateUser(User user) {
        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("User details are incomplete.");
        }
    }

    private Seat assignSeat(User user) {
        Seat seat;
        if (sectionA.size() <= sectionB.size()) {
            seat = new Seat(sectionA.size() + 1, "A");
            sectionA.put(user.getEmail(), seat);
        } else {
            seat = new Seat(sectionB.size() + 1, "B");
            sectionB.put(user.getEmail(), seat);
        }
        return seat;
    }


    public  ResponseEntity<Receipt> getReceiptByEmail(String email) {
        if (receiptsByUserEmail.containsKey(email)) {
            return  ResponseEntity.ok(receiptsByUserEmail.get(email));
        } else {
            throw new IllegalArgumentException("Receipt not found for the user with email: " + email);
        }
    }

    public Map<String, Seat> getUsersBySection(String section) {
        switch (section) {
            case "A":
                return sectionA;
            case "B":
                return sectionB;
            default:
                throw new IllegalArgumentException("Invalid section: " + section);
        }
    }

    public Response modifyUserSeat(String email, Seat newSeat) {
        // Check if user exists in either section
        boolean userInSectionA = sectionA.containsKey(email);
        boolean userInSectionB = sectionB.containsKey(email);

        if (!userInSectionA && !userInSectionB) {
            return new Response(0, "User not found.");
        }

        // Check if requested seat is already assigned to the user
        Seat currentSeat = userInSectionA ? sectionA.get(email) : sectionB.get(email);
        // Handle different scenarios
        if (currentSeat != null && currentSeat.equals(newSeat)) {
            return new Response(0, "User already assigned to the requested seat.");
        } else if (isSeatInSameSection(currentSeat, newSeat)) {
            // Modify seat within the same section if available
            if (userInSectionA) {
                sectionA.put(email, newSeat);
            } else if (userInSectionB) {
                sectionB.put(email, newSeat);
            }
            return new Response(1, "User's seat modified: " + email);
        } else {
            return new Response(0, "Requested seat not available or Cannot change seat to another section.");
        }
    }

    private boolean isSeatInSameSection(Seat seat1, Seat seat2) {
        return seat1.getSection().equals(seat2.getSection());
    }

    public Response removeUser(String email) {
        if (sectionA.containsKey(email) || sectionB.containsKey(email)) {
            sectionA.remove(email);
            sectionB.remove(email);
            receiptsByUserEmail.remove(email);
            return new Response(1, "User removed: " + email);
        } else {
            return new Response(0, "User not found " + email);
        }
    }
}
