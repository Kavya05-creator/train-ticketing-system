package com.train.ticket.service;

import com.train.ticket.model.Receipt;
import com.train.ticket.model.Response;
import com.train.ticket.model.Seat;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TicketService {
    ResponseEntity<Receipt> purchaseTicket(Receipt receipt);
    ResponseEntity<Receipt> getReceiptByEmail(String email);
    Map<String, Seat> getUsersBySection(String section);
    Response removeUser(String email);
    Response modifyUserSeat(String email, Seat newSeat);
}
