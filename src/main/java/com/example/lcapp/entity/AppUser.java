package com.example.lcapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Email(message = "Email Must Be Included")
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role roles;

    private boolean enabled;


}
