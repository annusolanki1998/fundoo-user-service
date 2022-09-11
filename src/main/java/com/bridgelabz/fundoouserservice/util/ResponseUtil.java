package com.bridgelabz.fundoouserservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Purpose : Response used to handle the exception
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Data
@AllArgsConstructor
public class ResponseUtil {
    private int errorCode;
    private String message;
    private Object token;


    public ResponseUtil() {

    }
}
