package com.laptrinh.carparkex.repository;

import com.laptrinh.carparkex.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employee, Long> {
}
