package com.PB.PhoneBook.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(name = "Phone_Number")
    @Pattern(regexp = "[0-9]{9}")
    private int phoneNumber;
    @Column(name = "Business_Phone_Number")
    @Pattern(regexp = "[0-9]{9}")
    private int businessPhoneNumber;
    @Email
    private String email;
    @JsonIgnore
    @ManyToOne
    private Customer customer;
}
