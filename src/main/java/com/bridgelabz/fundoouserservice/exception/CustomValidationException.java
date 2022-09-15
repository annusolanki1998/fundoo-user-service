package com.bridgelabz.fundoouserservice.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Purpose : CustomValidationException is used to validation exception
 * Version : 1.0
 * @author : Annu Kumari
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomValidationException {
    private int errorCode;
    private String message;
}
