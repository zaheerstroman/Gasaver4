package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDetailsResponseGasaverT {
//public class ProfileDetailsResponseGasaverT extends BaseResponse{


    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    @SerializedName("data")
    @Expose

//    ArrayList<Data> data;
//
//    public ArrayList<Data> getData() {
//        return data;
//    }
//
//    public void setData(ArrayList<Data> data) {
//        this.data = data;
//    }

//    private List<Datum> data;
//    private List<Data> data;
//    private ArrayList<Data> data;

//    ArrayList<Data> data;


    public Data data;
    public  Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }


    //    data
//    to
//    ProfileDetails profileDetails;


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

//    -----------------------------

//    List<Data> data;


//    public List<Data> getData() {
//        return data;
//    }
//
//    public void setData(List<Data> data) {
//        this.data = data;
//    }


//    ------------------------

    //    public ArrayList<Data> data;
//    ArrayList<Data> data;
//
//    public Data getData() {
//        return data;
//    }

//    public Data getData() {
//        return data;
//    }



    //    public class Datum {

    public static class Data {

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
        private Object name;
        @SerializedName("last_name")
        @Expose
        private Object lastName;
        @SerializedName("email")
        @Expose
        private Object email;
        @SerializedName("password")
        @Expose
        private Object password;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("gender")
        @Expose
        private Object gender;
        @SerializedName("dob")
        @Expose
        private Object dob;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("postCode")
        @Expose
        private Object postCode;
        @SerializedName("address")
        @Expose
        private Object address;
        @SerializedName("state")
        @Expose
        private Object state;
        @SerializedName("country")
        @Expose
        private Object country;
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
        private String fcmToken;
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

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getLastName() {
            return lastName;
        }

        public void setLastName(Object lastName) {
            this.lastName = lastName;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
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

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
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

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
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

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
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
