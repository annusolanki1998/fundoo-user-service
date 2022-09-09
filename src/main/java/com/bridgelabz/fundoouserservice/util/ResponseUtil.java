package com.bridgelabz.fundoouserservice.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUtil {
    private int statusCode;
    private String statusMessage;
    private Object object;

}
