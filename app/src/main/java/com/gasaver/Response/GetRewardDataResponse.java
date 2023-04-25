package com.gasaver.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetRewardDataResponse extends BaseResponse{

//public class GetRewardDataResponse extends BaseResponseGasaver{

    @SerializedName("data")
    ArrayList<GetRewardData> data;

    public ArrayList<GetRewardDataResponse.GetRewardData> getData() {
        return data;
    }

    public void setData(ArrayList<GetRewardDataResponse.GetRewardData> data) {
        this.data = data;
    }

    public class GetRewardData {
        @SerializedName("data")
        String id;
        @SerializedName("subcategory_id")
        String subcategory_id;
        @SerializedName("vendor_id")
        String vendor_id;
        @SerializedName("stationId")
        String stationId;
        @SerializedName("stationcode")
        String stationcode;
        @SerializedName("type")
        String type;
        @SerializedName("updated")
        String updated;
        @SerializedName("lastupdated")
        String lastupdated;
        @SerializedName("relevant")
        String relevant;
        @SerializedName("reportedBy")
        String reportedBy;
        @SerializedName("amount")
        String amount;
        @SerializedName("status")
        String status;
        @SerializedName("Progress")
        String Progress;
        @SerializedName("user_id")
        String user_id;
        @SerializedName("station_name")
        String station_name;
        @SerializedName("subcategory_name")
        String subcategory_name;
        @SerializedName("category_name")
        String category_name;
        @SerializedName("brand")
        String brand;
        @SerializedName("rewards")
        String rewards;

        public String getRewards() {
            return rewards;
        }

        public void setRewards(String rewards) {
            this.rewards = rewards;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(String subcategory_id) {
            this.subcategory_id = subcategory_id;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getStationcode() {
            return stationcode;
        }

        public void setStationcode(String stationcode) {
            this.stationcode = stationcode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getLastupdated() {
            return lastupdated;
        }

        public void setLastupdated(String lastupdated) {
            this.lastupdated = lastupdated;
        }

        public String getRelevant() {
            return relevant;
        }

        public void setRelevant(String relevant) {
            this.relevant = relevant;
        }

        public String getReportedBy() {
            return reportedBy;
        }

        public void setReportedBy(String reportedBy) {
            this.reportedBy = reportedBy;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProgress() {
            return Progress;
        }

        public void setProgress(String progress) {
            Progress = progress;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getStation_name() {
            return station_name;
        }

        public void setStation_name(String station_name) {
            this.station_name = station_name;
        }

        public String getSubcategory_name() {
            return subcategory_name;
        }

        public void setSubcategory_name(String subcategory_name) {
            this.subcategory_name = subcategory_name;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }

}
