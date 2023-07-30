package com.laptrinh.carparkex.repository;

import com.laptrinh.carparkex.dto.BookingOffResponse;
import com.laptrinh.carparkex.entity.BookingOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookOffRepo extends JpaRepository<BookingOffice, Long> {
    @Query(value = "select b.bookId, b.officeName, t.destination from BookingOffice b join b.trip t")
    List<BookingOffResponse> getAllBookingInformationWithJoin();

}
