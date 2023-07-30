package com.laptrinh.carparkex.repository;

import com.laptrinh.carparkex.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICarRepo extends JpaRepository<Car, Long> {
    @Query(value = "SELECT * FROM Car WHERE license_plate = ?", nativeQuery = true)
    Car getCarByLicense(String license);
}
