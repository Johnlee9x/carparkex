package com.laptrinh.carparkex.controller;

import com.laptrinh.carparkex.dto.TicketRequest;
import com.laptrinh.carparkex.dto.TicketResponse;
import com.laptrinh.carparkex.entity.Ticket;
import com.laptrinh.carparkex.exception.TicketNotFoundException;
import com.laptrinh.carparkex.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ticKet")
public class TicketController {
    @Autowired
    private TicketService service;
    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAll(){
        List<TicketResponse> list = new ArrayList<>();
        TicketResponse ticketResponse ;
        for(Ticket t: service.getALl()){
            ticketResponse = new TicketResponse();
            ticketResponse.setTicketId(t.getTicketId());
            ticketResponse.setDestination(t.getTrip().getDestination());
            ticketResponse.setCustomerName(t.getCustomerName());
            ticketResponse.setBookingTime(t.getBookingTime());
            ticketResponse.setLicensePlate(t.getCar().getLicensePlate());
            list.add(ticketResponse);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> addNewTicket(@Valid @RequestBody TicketRequest ticketRequest){
        return new ResponseEntity<>(service.addNewTicket(ticketRequest), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable(value = "id", required = false) Long id)
            throws TicketNotFoundException {
        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInforTicket(@PathVariable(value = "id", required = false) Long id,
                                               @Valid @RequestBody TicketRequest requestTicket)
            throws TicketNotFoundException {
        service.updateTicketById(id, requestTicket);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTicketById(@PathVariable(value = "id", required = false) Long id)
            throws TicketNotFoundException {
        service.deleteTicketById(id);
    }
}
