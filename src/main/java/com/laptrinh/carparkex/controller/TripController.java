package com.laptrinh.carparkex.controller;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.TripRequest;
import com.laptrinh.carparkex.dto.TripResponse;
import com.laptrinh.carparkex.exception.TripNotFoundException;
import com.laptrinh.carparkex.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private TripService service;
    @GetMapping
    public ResponseEntity<List<TripResponse>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{tripId}")
    public TripResponse getByTripId(@PathVariable(value = "tripId", required = false) Long tripId)
            throws TripNotFoundException {
        return ConfigMapperModel.getMapper().map(service.findById(tripId), TripResponse.class);
    }
    @PostMapping("/create")
    public ResponseEntity<TripResponse> save(@Valid @RequestBody TripRequest tripRequest){
        return new ResponseEntity<>(ConfigMapperModel.getMapper().map(service.saveNewTrip(tripRequest), TripResponse.class), HttpStatus.CREATED);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable(value = "id", required = false) Long id,
                                        @Valid @RequestBody TripRequest requestTrip)
            throws TripNotFoundException {
        return new ResponseEntity<>(service.updateTripById(id, requestTrip),HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteTrip(@PathVariable(value = "id", required = false) Long id)
            throws TripNotFoundException {
        service.deleteTripById(id);
    }

}
