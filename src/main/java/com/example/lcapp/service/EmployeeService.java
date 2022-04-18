package com.example.lcapp.service;

import com.example.lcapp.dto.EmployeeDto;
import com.example.lcapp.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    void addEmployee(EmployeeDto employeeDto);

    void deleteEmployee(Long id);

    void updateEmployee(Long id, EmployeeDto employeeDto);


}
