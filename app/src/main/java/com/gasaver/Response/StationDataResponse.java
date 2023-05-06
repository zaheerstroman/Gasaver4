package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StationDataResponse extends BaseResponse{

    @SerializedName("result")
    @Expose
    public ArrayList<StationDataModel> data;

    public ArrayList<StationDataModel> getData() {
        return data;
    }

    public void setData(ArrayList<StationDataModel> data) {
        this.data = data;
    }

    public class StationDataModel {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stationid")
        @Expose
        private String stationid;

        @SerializedName("vendor_id")
        @Expose
        private String vendorId;
        @SerializedName("date")
        @Expose
        private String date;

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("department_id")
        @Expose
        private Integer departmentId;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;

//        brand_icon
        @SerializedName("brand_icon")
        @Expose
        private String brandIcon;

        @SerializedName("wishlist")
        @Expose
        private String wishlist;
        @SerializedName("brandid")
        @Expose
        private Object brandid;
        @SerializedName("distance")
        @Expose
        private Integer distance;


        @SerializedName("prices")
        @Expose
        private ArrayList<PriceModel> prices;

        @SerializedName("default_data")
        @Expose
        private ArrayList<DefaultDataModel> default_data;


        public ArrayList<DefaultDataModel> getDefault_data() {
            return default_data;
        }

        public void setDefault_data(ArrayList<DefaultDataModel> default_data) {
            this.default_data = default_data;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
        //        public String getBrandIcon() {
//            return brandIcon;
//        }
//
//        public void setBrandIcon(String brandIcon) {
//            this.brandIcon = brandIcon;
//        }


        public String getVendorId() {
            return vendorId;
        }

        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }

        public String getBrandIcon() {
            return brandIcon;
        }

        public void setBrandIcon(String brandIcon) {
            this.brandIcon = brandIcon;
        }

        public String getWishlist() {
            return wishlist;
        }

        public void setWishlist(String wishlist) {
            this.wishlist = wishlist;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String  getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getBrandid() {
            return brandid;
        }

        public void setBrandid(Object brandid) {
            this.brandid = brandid;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public ArrayList<PriceModel> getPrices() {
            return prices;
        }

        public void setPrices(ArrayList<PriceModel> prices) {
            this.prices = prices;
        }



    }

    public class PriceModel {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;

        @SerializedName("lastupdated")
        @Expose
        private String lastupdated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getLastupdated() {
            return lastupdated;
        }

        public void setLastupdated(String lastupdated) {
            this.lastupdated = lastupdated;
        }
    }


}

//}
