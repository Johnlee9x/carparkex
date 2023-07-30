package com.laptrinh.carparkex.service;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.CarRequest;
import com.laptrinh.carparkex.entity.Car;
import com.laptrinh.carparkex.entity.ParkingLot;
import com.laptrinh.carparkex.entity.Ticket;
import com.laptrinh.carparkex.exception.CarNotFoundException;
import com.laptrinh.carparkex.repository.ICarRepo;
import com.laptrinh.carparkex.repository.IParkingLotRepo;
import com.laptrinh.carparkex.repository.ITicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private ICarRepo carRepo;
    @Autowired
    private IParkingLotRepo parkRepo;
    @Autowired
    private ITicketRepo ticketRepo;


    public List<Car> getAll(){
        return carRepo.findAll();
    }
    public Car addNewCar(CarRequest carRequest) {
        Car x;
        ParkingLot parkingLot = parkRepo.getReferenceById(carRequest.getParkId());
        List<Ticket> ticket = ticketRepo.getTicketByLicenseId(carRequest.getLicensePlate());
        x = ConfigMapperModel.getMapper().map(carRequest, Car.class);
        x.setTickets(ticket);
        x.setParkingLot(parkingLot);
        return carRepo.save(x);
    }
    public Car getCarByLicence(String license) throws CarNotFoundException {
        if (carRepo.getCarByLicense(license) != null){
            return carRepo.getCarByLicense(license);
        }
        throw new CarNotFoundException("Car Not Found With License: "+ license);
    }

    public void deleteACarByLicense(String license) throws CarNotFoundException {
        Car car = getCarByLicence(license);
        carRepo.delete(car);
    }

    public Car updateCarByLicense(String license, CarRequest carRequest) throws CarNotFoundException {
        Car car = getCarByLicence(license);
        car.setCarType(carRequest.getCarType());
        car.setCarColor(carRequest.getCarColor());
        car.setCompany(carRequest.getCompany());
        car.setLicensePlate(carRequest.getLicensePlate());
        return carRepo.save(car);
    }

}
