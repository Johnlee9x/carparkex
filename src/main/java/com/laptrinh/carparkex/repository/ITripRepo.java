package com.laptrinh.carparkex.repository;

import com.laptrinh.carparkex.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITripRepo extends JpaRepository<Trip, Long> {

}
