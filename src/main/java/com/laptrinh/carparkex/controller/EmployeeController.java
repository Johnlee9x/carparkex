package com.laptrinh.carparkex.controller;

import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import com.laptrinh.carparkex.dto.EmployeeRequest;
import com.laptrinh.carparkex.dto.EmployeeResponse;
import com.laptrinh.carparkex.entity.Employee;
import com.laptrinh.carparkex.exception.EmployeeNotFoundException;
import com.laptrinh.carparkex.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @PostMapping("/signup")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(service.saveEm(employeeRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        List<EmployeeResponse> list = new ArrayList<>();
        for(Employee e: service.getAll()){
            list.add(ConfigMapperModel.getMapper().map(e, EmployeeResponse.class));
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable(value = "id", required = false) Long id)
            throws EmployeeNotFoundException {
        return ConfigMapperModel.getMapper().map(service.getById(id), EmployeeResponse.class);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable(value = "id", required = false) Long id)
            throws EmployeeNotFoundException {
        service.deleteAEmployeeById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id", required = false) Long id,
                                           @Valid @RequestBody EmployeeRequest employeeRequest)
            throws EmployeeNotFoundException {
        return new ResponseEntity<>(service.updateInforEm(id, employeeRequest), HttpStatus.OK);
    }



}
