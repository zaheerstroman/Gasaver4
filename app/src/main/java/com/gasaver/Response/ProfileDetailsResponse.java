package com.gasaver.Response;

import com.google.gson.annotations.SerializedName;

public class ProfileDetailsResponse extends BaseResponse{


    @SerializedName("result")
    ProfileDetails profileDetails;

    public ProfileDetails getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(ProfileDetails profileDetails) {
        this.profileDetails = profileDetails;
    }

    public class ProfileDetails {

        @SerializedName("first_name")
        String first_name;
        @SerializedName("last_name")
        String last_name;
        @SerializedName("mobile")
        String mobile;
        @SerializedName("email")
        String email;
        @SerializedName("user_code")
        String user_code;
        @SerializedName("user_plan")
        String user_plan;
        @SerializedName("user_role")
        String user_role;
        @SerializedName("attachment")
        String attachment;

        public String getUser_role() {
            return user_role;
        }

        public void setUser_role(String user_role) {
            this.user_role = user_role;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getUser_plan() {
            return user_plan;
        }

        public void setUser_plan(String user_plan) {
            this.user_plan = user_plan;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }
    }

}
