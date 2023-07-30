package com.laptrinh.carparkex.repository;

import com.laptrinh.carparkex.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IParkingLotRepo extends JpaRepository<ParkingLot, Long> {
}
