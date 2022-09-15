package com.bridgelabz.fundoouserservice.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Purpose : Response used to handle the exception
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int statusCode;
    private String statusMessage;
    private Object object;

}
