package com.example.lcapp.serviceImpl;

import com.example.lcapp.Repository.EmployeeRepository;
import com.example.lcapp.dto.EmployeeDto;
import com.example.lcapp.entity.Employee;
import com.example.lcapp.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee of id " + id + " not found"));
    }

    public void addEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee();
        employee.setAddress(employeeDto.getAddress());
        employee.setAge(employeeDto.getAge());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Employee with id " + id + " not available"));
        employee.setAddress(employeeDto.getAddress());
        employee.setAge(employeeDto.getAge());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());

        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
