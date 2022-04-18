package com.example.lcapp.controller;

import com.example.lcapp.dto.EmployeeDto;
import com.example.lcapp.entity.Employee;
import com.example.lcapp.service.EmployeeService;
import com.example.lcapp.serviceImpl.EmployeeServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImplementation employeeServiceImplementation) {
        this.employeeService = employeeServiceImplementation;
    }

    @ModelAttribute("employeeInfo")
    public EmployeeDto createEmployeeDto() {
        return new EmployeeDto();
    }

    @RequestMapping("/data")
    public String homePage(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", employeeList);
        return "employee-data";
    }

    @RequestMapping("/Form")
    public String showEmployeeForm(@ModelAttribute("employeeInfo") EmployeeDto employeeDto) {
        return "add-employee-form";
    }

    @RequestMapping("/save")
    public String saveEmployee(@ModelAttribute("employeeInfo") EmployeeDto employeeDto) {
        employeeService.addEmployee(employeeDto);
        return "redirect:/employee/data";
    }

    @RequestMapping("/showFormForUpdate/{id}")

    public String updateForm(@PathVariable Long id, Model model) {
        Employee employeeForUpdate = employeeService.getEmployeeById(id);
        model.addAttribute("theId", id);
        model.addAttribute("employeeForUpdate", employeeForUpdate);
        //Set employee as model attribute to repopulate form

        return "update-form";
    }


    //NEW UPDATE METHOD
    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employeeInfo") EmployeeDto employeeDto) {
        employeeService.updateEmployee(id, employeeDto);

        return "redirect:/employee/data";
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee/data";

    }

}