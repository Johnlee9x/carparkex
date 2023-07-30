package com.laptrinh.carparkex.repository;

import com.laptrinh.carparkex.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRepo extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT * FROM ticket WHERE license_plate = ?", nativeQuery = true)
    List <Ticket> getTicketByLicenseId(String license);
}
