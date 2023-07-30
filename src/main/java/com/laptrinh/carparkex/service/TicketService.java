package com.laptrinh.carparkex.service;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.TicketRequest;
import com.laptrinh.carparkex.entity.Car;
import com.laptrinh.carparkex.entity.Ticket;
import com.laptrinh.carparkex.entity.Trip;
import com.laptrinh.carparkex.exception.TicketNotFoundException;
import com.laptrinh.carparkex.repository.ICarRepo;
import com.laptrinh.carparkex.repository.ITicketRepo;
import com.laptrinh.carparkex.repository.ITripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TicketService {
    @Autowired
    private ITicketRepo ticketRepo;
    @Autowired
    private ICarRepo carRepo;
    @Autowired
    private ITripRepo tripRepo;

    public List<Ticket> getALl(){
        return ticketRepo.findAll();
    }

    public Ticket findById(Long id) throws TicketNotFoundException {
        if(ticketRepo.findById(id).isPresent()){
            return ticketRepo.getReferenceById(id);
        }
        throw new TicketNotFoundException("Ticket Not Found With Id: "+ id);
    }

    public Ticket addNewTicket(TicketRequest ticketRequest){
        Car car = carRepo.getCarByLicense(ticketRequest.getLicensePlate());
        Trip trip = tripRepo.getReferenceById(ticketRequest.getTripId());
        Ticket ticket = ConfigMapperModel.getMapper().map(ticketRequest, Ticket.class);
        ticket.setTrip(trip);
        ticket.setCar(car);

        return ticketRepo.save(ticket);
    }

    public Ticket updateTicketById(Long id, TicketRequest requestTicket)
            throws TicketNotFoundException {
        Ticket ticket = findById(id);
        ticket.setCustomerName(requestTicket.getCustomerName());
        ticket.setBookingTime(requestTicket.getBookingTime());
        return ticketRepo.save(ticket);
    }

    public void deleteTicketById(Long id) throws TicketNotFoundException {
        Ticket ticket = findById(id);
        ticketRepo.delete(ticket);
    }

}
