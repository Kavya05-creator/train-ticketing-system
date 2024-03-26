package com.train.ticket.controllers;

import com.train.ticket.model.Receipt;
import com.train.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<Receipt> purchaseTicket(@RequestBody Receipt receipt) {
        return ticketService.purchaseTicket(receipt);
    }

    @GetMapping("/receipt/{email}")
    public  ResponseEntity<Receipt> viewReceipt(@PathVariable String email) {
        return ticketService.getReceiptByEmail(email);
    }
}
