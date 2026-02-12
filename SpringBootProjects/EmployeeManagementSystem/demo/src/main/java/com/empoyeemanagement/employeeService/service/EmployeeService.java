package com.empoyeemanagement.employeeService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empoyeemanagement.employeeService.Entity.Employee;
import com.empoyeemanagement.employeeService.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    //create or update employee
    public Employee createOrUpdateEmployee(Employee employee) {
        return employeeRepository.save(employee);
   }

    //get employee by id
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
    //get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //delete employee by id
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

}
