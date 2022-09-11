package com.bridgelabz.fundoouserservice.exception.exceptionhandler;

import com.bridgelabz.fundoouserservice.exception.CustomValidationException;
import com.bridgelabz.fundoouserservice.exception.FundooUserNotFoundException;
import com.bridgelabz.fundoouserservice.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FundooUserException {

    /*
     * Purpose : FundooUserNotFoundException is used to handle the exceptions
     * Version : 1.0
     * @author : Annu kumari
     * */

    @ExceptionHandler(FundooUserNotFoundException.class)
    public ResponseEntity<ResponseUtil> handlerHiringException(FundooUserNotFoundException exception) {
        ResponseUtil response = new ResponseUtil();
        response.setErrorCode(400);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Purpose : MethodArgumentNotValidException is used to handle the exceptions
     * Version : 1.0
     * @author : Annu kumari
     * */


    // Using custom exception for handling the error of validation part
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomValidationException> customValidationException(MethodArgumentNotValidException exception) {
        CustomValidationException customValidationException = new CustomValidationException();
        customValidationException.setErrorCode(400);
        customValidationException.setMessage(exception.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(customValidationException, HttpStatus.BAD_REQUEST);
    }


}
