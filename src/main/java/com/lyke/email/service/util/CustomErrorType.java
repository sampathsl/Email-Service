/**
 * Created by SAMPATH on 07/01/2017.
 * Email service error message data transfer class
 */

package com.lyke.email.service.util;

public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
