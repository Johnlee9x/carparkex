package com.laptrinh.carparkex.controller;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.ParkingLotRequest;
import com.laptrinh.carparkex.dto.ParkingLotResponse;
import com.laptrinh.carparkex.entity.ParkingLot;
import com.laptrinh.carparkex.exception.InCorrectInputException;
import com.laptrinh.carparkex.exception.ParkingLotNotFoundException;
import com.laptrinh.carparkex.service.ParkingLotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/parkingLot")
public class ParkingLotController {
    @Autowired
    private ParkingLotService service;
    @GetMapping
    private List<ParkingLotResponse> getAll(){
        List<ParkingLotResponse> lotResponses = new ArrayList<>();
        for(ParkingLot p: service.getAll()){
            lotResponses.add(ConfigMapperModel.getMapper().map(p, ParkingLotResponse.class));
        }
        return lotResponses;
    }
    @PostMapping("/create")
    public ResponseEntity<ParkingLotResponse> saveParkingLot(@RequestBody ParkingLotRequest parkingLotReq){
        return new ResponseEntity<>(ConfigMapperModel.getMapper().map(service.saveNewParking(parkingLotReq),ParkingLotResponse.class), HttpStatus.CREATED);
    }
    @GetMapping("/{parkId}")
    public ParkingLotResponse getParkingLotByParkId(
            @PathVariable(value = "parkId", required = false)
            @Valid Long id)
            throws ParkingLotNotFoundException {
        return ConfigMapperModel.getMapper().map(service.findByParkId(id), ParkingLotResponse.class);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAParkingLot(@PathVariable(value = "id", required = false) Long id,
                                               @Valid @RequestBody ParkingLotRequest requestParkingLot)
            throws ParkingLotNotFoundException {
        return new ResponseEntity<>(service.updateParkingLot(id, requestParkingLot) ,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAParkingLot(@PathVariable(value = "id", required = false) Long id)
            throws ParkingLotNotFoundException {
        service.deleteAParkingLotById(id);
    }

}
