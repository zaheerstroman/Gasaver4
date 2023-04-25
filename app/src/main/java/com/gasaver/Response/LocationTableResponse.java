package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationTableResponse extends BaseResponse{

    @SerializedName("data")
    ArrayList<LocationTable> locationTables;

    public ArrayList<LocationTable> getLocationTables() {
        return locationTables;
    }

    public void setLocationTables(ArrayList<LocationTable> locationTables) {
        this.locationTables = locationTables;
    }

    public class LocationTable {


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("state_id")
        @Expose
        private String stateId;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("log_date_created")
        @Expose
        private String logDateCreated;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("log_date_modified")
        @Expose
        private String logDateModified;
        @SerializedName("modified_by")
        @Expose
        private String modifiedBy;
        @SerializedName("priority")
        @Expose
//        private Object priority;
        private String priority;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLogDateCreated() {
            return logDateCreated;
        }

        public void setLogDateCreated(String logDateCreated) {
            this.logDateCreated = logDateCreated;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getLogDateModified() {
            return logDateModified;
        }

        public void setLogDateModified(String logDateModified) {
            this.logDateModified = logDateModified;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

//        public Object getPriority() {
//            return priority;
//        }
//
//        public void setPriority(Object priority) {
//            this.priority = priority;
//        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }
    }
}

