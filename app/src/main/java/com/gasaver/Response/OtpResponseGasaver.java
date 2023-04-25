package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OtpResponseGasaver {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user_details")
    @Expose
    List<UserDetails> userDetails;

//    private UserDetails userDetails;

//    @SerializedName("status_code")
//    @Expose
//    private Integer statusCode;

    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;

    @SerializedName("isVerified")
    @Expose
    private Integer isVerified;
    @SerializedName("loggedIn")
    @Expose
    private Integer loggedIn;
    @SerializedName("message")
    @Expose
    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

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


    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public Integer getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Integer loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class UserDetails{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("stationid")
        @Expose
        private Object stationid;
        @SerializedName("user_code")
        @Expose
        private String userCode;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("last_name")
        @Expose
        private Object lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private Object password;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("postCode")
        @Expose
        private Object postCode;
        @SerializedName("address")
        @Expose
        private Object address;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("street")
        @Expose
        private Object street;
        @SerializedName("proof_type")
        @Expose
        private Object proofType;
        @SerializedName("proof_attachment")
        @Expose
        private Object proofAttachment;
        @SerializedName("profile_photo")
        @Expose
        private Object profilePhoto;
        @SerializedName("fb_id")
        @Expose
        private Object fbId;
        @SerializedName("google_id")
        @Expose
        private Object googleId;
        @SerializedName("category_id")
        @Expose
        private Object categoryId;
        @SerializedName("about")
        @Expose
        private Object about;
        @SerializedName("expert_in_yrs")
        @Expose
        private Object expertInYrs;
        @SerializedName("NI_number")
        @Expose
        private Object nINumber;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("mobile_verified")
        @Expose
        private String mobileVerified;
        @SerializedName("is_verified")
        @Expose
        private String isVerified;
        @SerializedName("log_date_created")
        @Expose
        private Object logDateCreated;
        @SerializedName("created_by")
        @Expose
        private Object createdBy;
        @SerializedName("log_date_modified")
        @Expose
        private Object logDateModified;
        @SerializedName("modified_by")
        @Expose
        private Object modifiedBy;
        @SerializedName("fcm_token")
        @Expose
        private Object fcmToken;
        @SerializedName("device_id")
        @Expose
        private Object deviceId;
        @SerializedName("latitude")
        @Expose
        private Object latitude;
        @SerializedName("longitude")
        @Expose
        private Object longitude;
        @SerializedName("no_vendor")
        @Expose
        private String noVendor;
        @SerializedName("specifications")
        @Expose
        private Object specifications;
        @SerializedName("brand")
        @Expose
        private Object brand;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("code")
        @Expose
        private Object code;
        @SerializedName("suburb")
        @Expose
        private Object suburb;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public Object getStationid() {
            return stationid;
        }

        public void setStationid(Object stationid) {
            this.stationid = stationid;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getLastName() {
            return lastName;
        }

        public void setLastName(Object lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getPostCode() {
            return postCode;
        }

        public void setPostCode(Object postCode) {
            this.postCode = postCode;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Object getStreet() {
            return street;
        }

        public void setStreet(Object street) {
            this.street = street;
        }

        public Object getProofType() {
            return proofType;
        }

        public void setProofType(Object proofType) {
            this.proofType = proofType;
        }

        public Object getProofAttachment() {
            return proofAttachment;
        }

        public void setProofAttachment(Object proofAttachment) {
            this.proofAttachment = proofAttachment;
        }

        public Object getProfilePhoto() {
            return profilePhoto;
        }

        public void setProfilePhoto(Object profilePhoto) {
            this.profilePhoto = profilePhoto;
        }

        public Object getFbId() {
            return fbId;
        }

        public void setFbId(Object fbId) {
            this.fbId = fbId;
        }

        public Object getGoogleId() {
            return googleId;
        }

        public void setGoogleId(Object googleId) {
            this.googleId = googleId;
        }

        public Object getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public Object getAbout() {
            return about;
        }

        public void setAbout(Object about) {
            this.about = about;
        }

        public Object getExpertInYrs() {
            return expertInYrs;
        }

        public void setExpertInYrs(Object expertInYrs) {
            this.expertInYrs = expertInYrs;
        }

        public Object getnINumber() {
            return nINumber;
        }

        public void setnINumber(Object nINumber) {
            this.nINumber = nINumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMobileVerified() {
            return mobileVerified;
        }

        public void setMobileVerified(String mobileVerified) {
            this.mobileVerified = mobileVerified;
        }

        public String getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(String isVerified) {
            this.isVerified = isVerified;
        }

        public Object getLogDateCreated() {
            return logDateCreated;
        }

        public void setLogDateCreated(Object logDateCreated) {
            this.logDateCreated = logDateCreated;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getLogDateModified() {
            return logDateModified;
        }

        public void setLogDateModified(Object logDateModified) {
            this.logDateModified = logDateModified;
        }

        public Object getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(Object modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public Object getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(Object fcmToken) {
            this.fcmToken = fcmToken;
        }

        public Object getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Object deviceId) {
            this.deviceId = deviceId;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getNoVendor() {
            return noVendor;
        }

        public void setNoVendor(String noVendor) {
            this.noVendor = noVendor;
        }

        public Object getSpecifications() {
            return specifications;
        }

        public void setSpecifications(Object specifications) {
            this.specifications = specifications;
        }

        public Object getBrand() {
            return brand;
        }

        public void setBrand(Object brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getSuburb() {
            return suburb;
        }

        public void setSuburb(Object suburb) {
            this.suburb = suburb;
        }
    }


}
