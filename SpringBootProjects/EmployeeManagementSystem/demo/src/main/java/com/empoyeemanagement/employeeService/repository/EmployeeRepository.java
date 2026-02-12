package com.empoyeemanagement.employeeService.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.empoyeemanagement.employeeService.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    

}