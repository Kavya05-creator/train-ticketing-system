package com.train.ticket.controllers;

import com.train.ticket.model.Response;
import com.train.ticket.model.Seat;
import com.train.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/section/{section}")
    public Map<String, Seat> getUsersBySection(@PathVariable String section) {
        return ticketService.getUsersBySection(section);
    }

    @PutMapping("/modify/{email}")
    public Response modifyUserSeat(@PathVariable String email, @RequestBody Seat newSeat) {
        return ticketService.modifyUserSeat(email, newSeat);
    }

    @DeleteMapping("/remove/{email}")
    public Response removeUser(@PathVariable String email) {
        return ticketService.removeUser(email);
    }
}
