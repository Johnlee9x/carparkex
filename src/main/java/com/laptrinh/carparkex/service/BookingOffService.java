package com.laptrinh.carparkex.service;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.BookingOffRequest;
import com.laptrinh.carparkex.entity.BookingOffice;
import com.laptrinh.carparkex.exception.BookOfficeNotFoundException;
import com.laptrinh.carparkex.exception.TripIdExisted;
import com.laptrinh.carparkex.repository.IBookOffRepo;
import com.laptrinh.carparkex.repository.ITripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingOffService {
    @Autowired
    private IBookOffRepo bookOffRepo;
    @Autowired
    private ITripRepo tripRepo;
    public BookingOffice saveBookingOffice(BookingOffRequest bookingOffRequest)
            throws TripIdExisted {
        BookingOffice bookingOffice = ConfigMapperModel.getMapper()
                .map(bookingOffRequest, BookingOffice.class);
        Long id = bookingOffRequest.getTripId();
        if(tripRepo.findById(id).isPresent()){
            bookingOffice.setTrip(tripRepo.getReferenceById(id));
            return bookOffRepo.save(bookingOffice);
        }
        else{
            throw new TripIdExisted("Trip Not Existed With TripId: "+ id);
        }
    }
    public List<BookingOffice> getAll(){
        return bookOffRepo.findAll();
    }

    public BookingOffice getBookOfficeById(Long id) throws BookOfficeNotFoundException {
        if(bookOffRepo.findById(id).isPresent()){
            return bookOffRepo.getReferenceById(id);
        }
        else {
            throw new BookOfficeNotFoundException("BookOffice Not Found Exception With Id: "+ id);
        }
    }
    public void deleteABookOfficeById(Long id) throws BookOfficeNotFoundException {
        BookingOffice bookingOffice = bookOffRepo.getReferenceById(id);
        if(bookOffRepo.findById(id).isPresent()){
            bookOffRepo.delete(bookingOffice);
        }
        else{
            throw new BookOfficeNotFoundException("Id Not Existed");
        }
    }
    public BookingOffice updateBookOffice(Long id,BookingOffRequest requestBookOff)
            throws BookOfficeNotFoundException {
        BookingOffice x = getBookOfficeById(id);
        x.setOfficeName(requestBookOff.getOfficeName());
        x.setOfficePlace(requestBookOff.getOfficePlace());
        x.setOfficePrice(requestBookOff.getOfficePrice());
        x.setOfficePhone(requestBookOff.getOfficePhone());
        x.setEndContactDeadline(requestBookOff.getEndContactDeadline());
        x.setStartContactDeadline(requestBookOff.getStartContactDeadline());
        return bookOffRepo.save(x);
    }


}
