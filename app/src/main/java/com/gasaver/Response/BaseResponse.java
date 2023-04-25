package com.gasaver.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;


//@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseResponse {

    @SerializedName("status_code")
    boolean status_code;
    @SerializedName("status")
    boolean status;
    @SerializedName("errorCode")
    int errorCode;
    @SerializedName("msg")
    String msg;
    @SerializedName("message")
    String message;

    public boolean isStatus_code() {
        return status_code;
    }

    public void setStatus_code(boolean status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
