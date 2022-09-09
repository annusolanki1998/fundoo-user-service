package com.bridgelabz.fundoouserservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Purpose : AdminNotFoundException is used to handle the exceptions
 * Version : 1.0
 * @author : Annu kumari
 * */

@ResponseStatus
public class FundooUserNotFoundException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public FundooUserNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
