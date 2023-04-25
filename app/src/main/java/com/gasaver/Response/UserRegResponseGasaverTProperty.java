package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserRegResponseGasaverTProperty {

    @SerializedName("result")
    @Expose
//    private Result result;

//    List<Result> userDetails;
//    public List<Result> getResult() {
//        return result;
//    }
//
//    public void setResult(List<Result> result) {
//        this.result = result;
//    }

    ArrayList<Result> result;

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

//    ArrayList<UserDetails> userDetails;
//    public ArrayList<UserDetails> getUserDetails() {
//        return userDetails;
//    }
//
//    public void setUserDetails(ArrayList<UserDetails> userDetails) {
//        this.userDetails = userDetails;
//    }

    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;
    @SerializedName("message")
    @Expose
    private String message;


    public class Result {


        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user_mobile")
        @Expose
        private String userMobile;
        @SerializedName("api_token")
        @Expose
        private String apiToken;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public String getApiToken() {
            return apiToken;
        }

        public void setApiToken(String apiToken) {
            this.apiToken = apiToken;
        }
    }


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
