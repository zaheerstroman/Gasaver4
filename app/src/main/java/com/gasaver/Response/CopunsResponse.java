package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CopunsResponse {

    public static String BASE_URL = "";


    @SerializedName("copuns_details")
    @Expose
//    private List<CopunsDetail> copunsDetails;
    private ArrayList<CopunsDetail> copunsDetails;

    @SerializedName("base_path")
    @Expose
    private String basePath;
    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;

    public ArrayList<CopunsDetail> getCopunsDetails() {
        return copunsDetails;
    }

    public void setCopunsDetails(ArrayList<CopunsDetail> copunsDetails) {
        this.copunsDetails = copunsDetails;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public class CopunsDetail{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("attachment")
        @Expose
        private String attachment;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("logged_date")
        @Expose
        private String loggedDate;
        @SerializedName("created_by")
        @Expose
        private Integer createdBy;
        @SerializedName("updated_date")
        @Expose
        private Object updatedDate;
        @SerializedName("updated_by")
        @Expose
        private Object updatedBy;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLoggedDate() {
            return loggedDate;
        }

        public void setLoggedDate(String loggedDate) {
            this.loggedDate = loggedDate;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
        }

        public Object getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(Object updatedDate) {
            this.updatedDate = updatedDate;
        }

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
            this.updatedBy = updatedBy;
        }
    }


}
