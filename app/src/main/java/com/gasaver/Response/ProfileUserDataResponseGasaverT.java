package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//public class ProfileUserDataResponseGasaverT {
public class ProfileUserDataResponseGasaverT extends BaseResponseGasaverTProperty{



//    @SerializedName("status_code")
//    @Expose
//    private Integer statusCode;

//    @SerializedName("status_code")
//    @Expose
//    private Boolean statusCode;
//
//    @SerializedName("message")
//    @Expose
//    private String message;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

//    ----------------

    @SerializedName("base_path")
    @Expose
    private String base_path;

    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("bar_code")
    @Expose
    private String barCode;

    @SerializedName("reward_points")
    @Expose
    private Integer rewardPoints;



    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getBase_path() {
        return base_path;
    }

    public void setBase_path(String base_path) {
        this.base_path = base_path;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    //    --------------------------------------------------

//    private List<Data> data;
//
//    public List<Data> getData() {
//        return data;
//    }
//
//    public void setData(List<Data> data) {
//        this.data = data;
//    }

//    ---------------------------------------------------

//    public Integer getStatusCode() {
//        return statusCode;
//    }

//    public void setStatusCode(Integer statusCode) {
//        this.statusCode = statusCode;
//    }


//    public Boolean getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(Boolean statusCode) {
//        this.statusCode = statusCode;
//    }




    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("stationid")
        @Expose
//        private Object stationid;
        private String stationid;
        @SerializedName("user_code")
        @Expose
        private String userCode;
        @SerializedName("name")
        @Expose
//        private Object name;
        private String name;
        @SerializedName("last_name")
        @Expose
//        private Object lastName;
        private String lastName;
        @SerializedName("email")
        @Expose
//        private Object email;
        private String email;
        @SerializedName("password")
        @Expose
//        private Object password;
        private String password;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("gender")
        @Expose
//        private Object gender;
        private String gender;
        @SerializedName("dob")
        @Expose
//        private Object dob;
        private String dob;
        @SerializedName("city")
        @Expose
//        private Object city;
        private String city;
        @SerializedName("postCode")
        @Expose
//        private Object postCode;
        private String postCode;
        @SerializedName("address")
        @Expose
//        private Object address;
        private String address;
        @SerializedName("state")
        @Expose
//        private Object state;
        private String state;
        @SerializedName("country")
        @Expose
//        private Object country;
        private String country;
        @SerializedName("street")
        @Expose
//        private Object street;
        private String street;
        @SerializedName("proof_type")
        @Expose
//        private Object proofType;
        private String proofType;
        @SerializedName("proof_attachment")
        @Expose
//        private Object proofAttachment;
        private String proofAttachment;
        @SerializedName("profile_photo")
        @Expose
//        private Object profilePhoto;
        private String profilePhoto;
        @SerializedName("fb_id")
        @Expose
//        private Object fbId;
        private String fbId;
        @SerializedName("google_id")
        @Expose
//        private Object googleId;
        private String googleId;
        @SerializedName("category_id")
        @Expose
//        private Object categoryId;
        private String categoryId;
        @SerializedName("about")
        @Expose
//        private Object about;
        private String about;
        @SerializedName("expert_in_yrs")
        @Expose
//        private Object expertInYrs;
        private String expertInYrs;
        @SerializedName("NI_number")
        @Expose
//        private Object nINumber;
        private String nINumber;
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
//        private Object logDateCreated;
        private String logDateCreated;
        @SerializedName("created_by")
        @Expose
//        private Object createdBy;
        private String createdBy;
        @SerializedName("log_date_modified")
        @Expose
//        private Object logDateModified;
        private String logDateModified;
        @SerializedName("modified_by")
        @Expose
//        private Object modifiedBy;
        private String modifiedBy;
        @SerializedName("fcm_token")
        @Expose
        private String fcmToken;
        @SerializedName("device_id")
        @Expose
//        private Object deviceId;
        private String deviceId;
        @SerializedName("latitude")
        @Expose
//        private Object latitude;
        private String latitude;
        @SerializedName("longitude")
        @Expose
//        private Object longitude;
        private String longitude;
        @SerializedName("no_vendor")
        @Expose
        private String noVendor;
        @SerializedName("specifications")
        @Expose
//        private Object specifications;
        private String specifications;
        @SerializedName("brand")
        @Expose
//        private Object brand;
        private String brand;
        @SerializedName("brandid")
        @Expose
//        private Object brandid;
        private String brandid;
        @SerializedName("code")
        @Expose
//        private Object code;
        private String code;
        @SerializedName("suburb")
        @Expose
//        private Object suburb;
        private String suburb;
        @SerializedName("allow_push")
        @Expose
        private String allow_push;
        @SerializedName("allow_mail")
        @Expose
        private String allow_mail;
        @SerializedName("allow_sms")
        @Expose
        private String allow_sms;

        public String getAllow_push() {
            return allow_push;
        }

        public void setAllow_push(String allow_push) {
            this.allow_push = allow_push;
        }

        public String getAllow_mail() {
            return allow_mail;
        }

        public void setAllow_mail(String allow_mail) {
            this.allow_mail = allow_mail;
        }

        public String getAllow_sms() {
            return allow_sms;
        }

        public void setAllow_sms(String allow_sms) {
            this.allow_sms = allow_sms;
        }

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

//        public Object getStationid() {
//            return stationid;
//        }
//
//        public void setStationid(Object stationid) {
//            this.stationid = stationid;
//        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

//        public Object getName() {
//            return name;
//        }
//
//        public void setName(Object name) {
//            this.name = name;
//        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

//        public Object getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(Object lastName) {
//            this.lastName = lastName;
//        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

//        public Object getEmail() {
//            return email;
//        }
//
//        public void setEmail(Object email) {
//            this.email = email;
//        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

//        public Object getPassword() {
//            return password;
//        }
//
//        public void setPassword(Object password) {
//            this.password = password;
//        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

//        public Object getGender() {
//            return gender;
//        }
//
//        public void setGender(Object gender) {
//            this.gender = gender;
//        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

//        public Object getDob() {
//            return dob;
//        }
//
//        public void setDob(Object dob) {
//            this.dob = dob;
//        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

//        public Object getCity() {
//            return city;
//        }
//
//        public void setCity(Object city) {
//            this.city = city;
//        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

//        public Object getPostCode() {
//            return postCode;
//        }
//
//        public void setPostCode(Object postCode) {
//            this.postCode = postCode;
//        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

//        public Object getAddress() {
//            return address;
//        }
//
//        public void setAddress(Object address) {
//            this.address = address;
//        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }


//        public Object getState() {
//            return state;
//        }
//
//        public void setState(Object state) {
//            this.state = state;
//        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

//        public Object getCountry() {
//            return country;
//        }
//
//        public void setCountry(Object country) {
//            this.country = country;
//        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

//        public Object getStreet() {
//            return street;
//        }
//
//        public void setStreet(Object street) {
//            this.street = street;
//        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }


//        public Object getProofType() {
//            return proofType;
//        }
//
//        public void setProofType(Object proofType) {
//            this.proofType = proofType;
//        }

        public String getProofType() {
            return proofType;
        }

        public void setProofType(String proofType) {
            this.proofType = proofType;
        }

//        public Object getProofAttachment() {
//            return proofAttachment;
//        }
//
//        public void setProofAttachment(Object proofAttachment) {
//            this.proofAttachment = proofAttachment;
//        }

        public String getProofAttachment() {
            return proofAttachment;
        }

        public void setProofAttachment(String proofAttachment) {
            this.proofAttachment = proofAttachment;
        }

//        public Object getProfilePhoto() {
//            return profilePhoto;
//        }
//
//        public void setProfilePhoto(Object profilePhoto) {
//            this.profilePhoto = profilePhoto;
//        }

        public String getProfilePhoto() {
            return profilePhoto;
        }

        public void setProfilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
        }

//        public Object getFbId() {
//            return fbId;
//        }
//
//        public void setFbId(Object fbId) {
//            this.fbId = fbId;
//        }

        public String getFbId() {
            return fbId;
        }

        public void setFbId(String fbId) {
            this.fbId = fbId;
        }

//        public Object getGoogleId() {
//            return googleId;
//        }
//
//        public void setGoogleId(Object googleId) {
//            this.googleId = googleId;
//        }

        public String getGoogleId() {
            return googleId;
        }

        public void setGoogleId(String googleId) {
            this.googleId = googleId;
        }

//        public Object getCategoryId() {
//            return categoryId;
//        }
//
//        public void setCategoryId(Object categoryId) {
//            this.categoryId = categoryId;
//        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

//        public Object getAbout() {
//            return about;
//        }
//
//        public void setAbout(Object about) {
//            this.about = about;
//        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

//        public Object getExpertInYrs() {
//            return expertInYrs;
//        }
//
//        public void setExpertInYrs(Object expertInYrs) {
//            this.expertInYrs = expertInYrs;
//        }

        public String getExpertInYrs() {
            return expertInYrs;
        }

        public void setExpertInYrs(String expertInYrs) {
            this.expertInYrs = expertInYrs;
        }

//        public Object getnINumber() {
//            return nINumber;
//        }
//
//        public void setnINumber(Object nINumber) {
//            this.nINumber = nINumber;
//        }

        public String getnINumber() {
            return nINumber;
        }

        public void setnINumber(String nINumber) {
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

//        public Object getLogDateCreated() {
//            return logDateCreated;
//        }
//
//        public void setLogDateCreated(Object logDateCreated) {
//            this.logDateCreated = logDateCreated;
//        }

        public String getLogDateCreated() {
            return logDateCreated;
        }

        public void setLogDateCreated(String logDateCreated) {
            this.logDateCreated = logDateCreated;
        }

//        public Object getCreatedBy() {
//            return createdBy;
//        }
//
//        public void setCreatedBy(Object createdBy) {
//            this.createdBy = createdBy;
//        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

//        public Object getLogDateModified() {
//            return logDateModified;
//        }
//
//        public void setLogDateModified(Object logDateModified) {
//            this.logDateModified = logDateModified;
//        }

        public String getLogDateModified() {
            return logDateModified;
        }

        public void setLogDateModified(String logDateModified) {
            this.logDateModified = logDateModified;
        }

//        public Object getModifiedBy() {
//            return modifiedBy;
//        }
//
//        public void setModifiedBy(Object modifiedBy) {
//            this.modifiedBy = modifiedBy;
//        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
        }

//        public Object getDeviceId() {
//            return deviceId;
//        }
//
//        public void setDeviceId(Object deviceId) {
//            this.deviceId = deviceId;
//        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

//        public Object getLatitude() {
//            return latitude;
//        }
//
//        public void setLatitude(Object latitude) {
//            this.latitude = latitude;
//        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

//        public Object getLongitude() {
//            return longitude;
//        }
//
//        public void setLongitude(Object longitude) {
//            this.longitude = longitude;
//        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getNoVendor() {
            return noVendor;
        }

        public void setNoVendor(String noVendor) {
            this.noVendor = noVendor;
        }

//        public Object getSpecifications() {
//            return specifications;
//        }
//
//        public void setSpecifications(Object specifications) {
//            this.specifications = specifications;
//        }

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

//        public Object getBrand() {
//            return brand;
//        }
//
//        public void setBrand(Object brand) {
//            this.brand = brand;
//        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

//        public Object getBrandid() {
//            return brandid;
//        }
//
//        public void setBrandid(Object brandid) {
//            this.brandid = brandid;
//        }

        public String getBrandid() {
            return brandid;
        }

        public void setBrandid(String brandid) {
            this.brandid = brandid;
        }

//        public Object getCode() {
//            return code;
//        }
//
//        public void setCode(Object code) {
//            this.code = code;
//        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

//        public Object getSuburb() {
//            return suburb;
//        }
//
//        public void setSuburb(Object suburb) {
//            this.suburb = suburb;
//        }

        public String getSuburb() {
            return suburb;
        }

        public void setSuburb(String suburb) {
            this.suburb = suburb;
        }
    }
}
