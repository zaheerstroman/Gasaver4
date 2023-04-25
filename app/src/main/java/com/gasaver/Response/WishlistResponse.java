package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WishlistResponse extends BaseResponse{

    @SerializedName("result")
    @Expose
//    private List<Result> result;
    private ArrayList<WishlistModel> data;

    public ArrayList<WishlistModel> getData() {
        return data;
    }

    public void setData(ArrayList<WishlistModel> data) {
        this.data = data;
    }

    public class WishlistModel {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_code")
        @Expose
//        private Object userCode;
        private String userCode;

//        vendor_id
        @SerializedName("vendor_id")
        @Expose
        private String vendorId;

        @SerializedName("stationid")
        @Expose
        private String stationid;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("address")
        @Expose
        private String address;

        @SerializedName("wishlist")
        @Expose
        private String wishlist;

        @SerializedName("city")
        @Expose
//        private Object city;
        private String city;

        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("prices")
        @Expose
//        private List<Price> prices;
        private ArrayList<PriceModel> prices;

        @SerializedName("brand_icon")
        @Expose
        private String brandIcon;



        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getVendorId() {
            return vendorId;
        }

        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public ArrayList<PriceModel> getPrices() {
            return prices;
        }

        public void setPrices(ArrayList<PriceModel> prices) {
            this.prices = prices;
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
}
