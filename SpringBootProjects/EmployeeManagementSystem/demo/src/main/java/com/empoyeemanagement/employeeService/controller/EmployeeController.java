package com.empoyeemanagement.employeeService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empoyeemanagement.employeeService.Entity.Employee;
import com.empoyeemanagement.employeeService.service.EmployeeService;

import jakarta.persistence.Id;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
@Autowired
private EmployeeService employeeService;

//create or update employee
@PostMapping("/createOrUpdate")
public Employee createOrUpdateEmployee(@RequestBody Employee employee) {    
    return employeeService.createOrUpdateEmployee(employee);
}

    // Get Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//get all employees
@GetMapping("/getAllEmployees")
public ResponseEntity<List<Employee>> getAllEmployees(){
    List<Employee> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
}

//delete employee by id
@DeleteMapping("/{id}")
public void deleteEmployee(int id){
  employeeService.deleteEmployeeById(id);

}
}
