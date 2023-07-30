package com.laptrinh.carparkex.controller;

import com.laptrinh.carparkex.dto.BookingOffRequest;
import com.laptrinh.carparkex.dto.BookingOffResponse;
import com.laptrinh.carparkex.entity.BookingOffice;
import com.laptrinh.carparkex.exception.BookOfficeNotFoundException;
import com.laptrinh.carparkex.exception.TripIdExisted;

import com.laptrinh.carparkex.service.BookingOffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookingOffice")
public class BookingOffController {
    @Autowired
    private BookingOffService bookingOffService;

    @GetMapping
    public List<BookingOffResponse> getAll(){
        List<BookingOffResponse> responseList = new ArrayList<>();
        BookingOffResponse responseBooking;
        for(BookingOffice b : bookingOffService.getAll()){
            responseBooking = new BookingOffResponse();
            responseBooking.setBookId(b.getBookId());
            responseBooking.setTripName(b.getTrip().getDestination());
            responseBooking.setOfficeName(b.getOfficeName());
            responseList.add(responseBooking);
        }
        return responseList;
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@Valid @RequestBody BookingOffRequest bookingOffRequest) throws TripIdExisted {
        return new ResponseEntity<>(bookingOffService.saveBookingOffice(bookingOffRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public void updateBookOffice(@PathVariable(value = "id", required = false) Long id,
                                 @Valid @RequestBody BookingOffRequest bookingOffRequest )
                                 throws BookOfficeNotFoundException {
        bookingOffService.updateBookOffice(id, bookingOffRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBookingOfficeById(@PathVariable(value = "id", required = false) Long id)
            throws BookOfficeNotFoundException {
        bookingOffService.deleteABookOfficeById(id);
    }
}
