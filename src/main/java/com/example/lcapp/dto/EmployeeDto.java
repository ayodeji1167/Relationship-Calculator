package com.example.lcapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDto implements Serializable {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String address;
    @Email
    private String email;
    @NotNull
    private Integer age;
}
