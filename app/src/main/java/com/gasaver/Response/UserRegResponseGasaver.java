package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegResponseGasaver {

//    @SerializedName("status_code")
//    @Expose
//    private Integer statusCode;

    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;

    @SerializedName("message")
    @Expose
    private String message;

//    public Integer getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(Integer statusCode) {
//        this.statusCode = statusCode;
//    }


    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
