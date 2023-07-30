package com.laptrinh.carparkex.controller;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.CarRequest;
import com.laptrinh.carparkex.dto.CarResponse;
import com.laptrinh.carparkex.entity.Car;
import com.laptrinh.carparkex.exception.CarNotFoundException;
import com.laptrinh.carparkex.service.CarService;
import com.laptrinh.carparkex.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private TripService tripService;

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAll(){
        List<CarResponse> responseList = new ArrayList<>();
        for (Car car: carService.getAll()){
            responseList.add(ConfigMapperModel.getMapper().map(car, CarResponse.class));
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addNewCar(@Valid @RequestBody CarRequest carRequest) {
        return new ResponseEntity<>(carService.addNewCar(carRequest), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable(value = "id", required = false) String id)
            throws CarNotFoundException {
        return new ResponseEntity<>(ConfigMapperModel.getMapper().map(carService.getCarByLicence(id), CarResponse.class), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCarByLicense(@PathVariable(value = "id", required = false) String license,
                                                @RequestBody CarRequest carRequest) throws CarNotFoundException {
        return new ResponseEntity<>(carService.updateCarByLicense(license, carRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCarByLicense(@PathVariable(value = "id", required = false) String license)
            throws CarNotFoundException {
        carService.deleteACarByLicense(license);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
