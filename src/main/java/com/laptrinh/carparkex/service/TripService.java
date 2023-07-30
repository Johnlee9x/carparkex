package com.laptrinh.carparkex.service;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.TripRequest;
import com.laptrinh.carparkex.dto.TripResponse;

import com.laptrinh.carparkex.entity.Trip;
import com.laptrinh.carparkex.repository.IBookOffRepo;
import com.laptrinh.carparkex.repository.ITicketRepo;
import com.laptrinh.carparkex.repository.ITripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laptrinh.carparkex.exception.TripNotFoundException;
import java.util.ArrayList;

import java.util.List;

@Service
public class TripService {
    @Autowired
    private ITripRepo tripRepo;
    @Autowired
    private IBookOffRepo bookingRepo;
    @Autowired
    private ITicketRepo ticketRepo;

    public List<TripResponse> getAll(){
        List<TripResponse> list = new ArrayList<>();
        for(Trip t: tripRepo.findAll()){
            list.add(ConfigMapperModel.getMapper().map(t, TripResponse.class));
        }
        return list;
    }
    public Trip findById(Long tripId) throws TripNotFoundException {
        if(tripRepo.findById(tripId).isPresent()){
            return tripRepo.getReferenceById(tripId);
        }
        else{
            throw new TripNotFoundException("Trip Not Found With Id: "+ tripId);
        }
    }
    public Trip saveNewTrip(TripRequest tripRequest){
        return tripRepo.save(ConfigMapperModel.getMapper().map(tripRequest, Trip.class));
    }
    public void deleteTripById(Long tripId) throws TripNotFoundException {
        Trip trip = findById(tripId);
        tripRepo.delete(trip);
    }

    public Trip updateTripById(Long id, TripRequest tripRequest) throws TripNotFoundException {
        Trip trip = findById(id);
        trip.setDestination(tripRequest.getDestination());
        trip.setDriver(tripRequest.getDriver());
        trip.setCarType(tripRequest.getCarType());
        trip.setDepartureDate(tripRequest.getDepartureDate());
        trip.setDepartureTime(tripRequest.getDepartureTime());
        trip.setMaxOnlineTicketNumber(tripRequest.getMaxOnlineTicketNumber());
        return tripRepo.save(trip);
    }


}
