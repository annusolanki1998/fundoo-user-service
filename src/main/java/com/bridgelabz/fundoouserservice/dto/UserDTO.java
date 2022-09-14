package com.bridgelabz.fundoouserservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/*
 * Purpose : UserDTO are used to create and update fundoo user
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Data
public class UserDTO {

    @NotNull(message = "Name should not be empty ")
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Invalid name")
    private String name;

    @NotNull(message = "Email id should not be empty")
    @Pattern(regexp = "[a-z][A-Z a-z 0-9]+[@][a-z]+[.][a-z]{2,}", message = "Invalid email id")
    private String emailId;

    @NotNull(message = "Password should not be empty")
    @Pattern(regexp = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}", message = "Invalid password")
    private String password;

    @NotNull(message = "DOB should not be empty")
    private Date DOB;

    @NotNull(message = "PhoneNumber should not be empty")
    @Pattern(regexp = "[+]91 [6-9]\\d{9}", message = "Invalid phone number")
    private String phoneNumber;



}
