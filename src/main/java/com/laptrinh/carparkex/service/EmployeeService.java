package com.laptrinh.carparkex.service;

import com.laptrinh.carparkex.dto.EmployeeRequest;

import com.laptrinh.carparkex.dto.EmployeeResponse;
import com.laptrinh.carparkex.entity.Employee;
import com.laptrinh.carparkex.exception.EmployeeNotFoundException;
import com.laptrinh.carparkex.repository.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.laptrinh.carparkex.Mapper.ConfigMapperModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private IEmployeeRepo employeeRepo;
    public List<Employee> getAll(){
        return employeeRepo.findAll();
    }
    public Employee getById(Long id) throws EmployeeNotFoundException {
        if(employeeRepo.findById(id).isPresent()){
           return employeeRepo.getReferenceById(id);
        }
        else{
            throw new EmployeeNotFoundException("Employee Not Found With ID: " + id);
        }

    }
    public Employee saveEm(EmployeeRequest employeeRequest){
        return employeeRepo.save(ConfigMapperModel.getMapper().map(employeeRequest, Employee.class));
    }
    public void deleteAEmployeeById(Long id)
            throws EmployeeNotFoundException {
        Employee employee = getById(id);
        employeeRepo.delete(employee);
    }

    public Employee updateInforEm(Long id, EmployeeRequest requestEm)
            throws EmployeeNotFoundException {
        Employee employee = getById(id);
        employee.setAccount(requestEm.getAccount());
        employee.setBirthday(requestEm.getBirthday());
        employee.setEmail(requestEm.getEmail());
        employee.setDepartment(requestEm.getDepartment());
        employee.setAddress(requestEm.getAddress());
        employee.setSex(requestEm.getSex());
        employee.setPhone(requestEm.getPhone());
        employee.setFullName(requestEm.getFullName());
        employee.setPassword(requestEm.getPassword());
        return employeeRepo.save(employee);
    }


}
