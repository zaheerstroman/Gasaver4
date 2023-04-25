package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannersResponse extends BaseResponse {

    public static String BASE_URL = "";

    @SerializedName("adds_details")
    @Expose
//    private List<AddsDetail> addsDetails;
    private ArrayList<AddsDetail> addsDetails;

    @Expose
    @SerializedName("company_details")
    private ArrayList<CompanyDetail> companyDetails;
//    private ArrayList<CompanyDetails> companyDetails;
//private List<CompanyDetail> companyDetails;

    @SerializedName("base_path")
    @Expose
    private String basePath;

    @SerializedName("company_base_path")
    @Expose
    private String companyBasePath;



    public ArrayList<AddsDetail> getAddsDetails() {
        return addsDetails;
    }

    public void setAddsDetails(ArrayList<AddsDetail> addsDetails) {
        this.addsDetails = addsDetails;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }


    public ArrayList<CompanyDetail> getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(ArrayList<CompanyDetail> companyDetails) {
        this.companyDetails = companyDetails;
    }

    public String getCompanyBasePath() {
        return companyBasePath;
    }

    public void setCompanyBasePath(String companyBasePath) {
        this.companyBasePath = companyBasePath;
    }

    //    public class Banner {
    public class AddsDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("user_type")
        @Expose
        private String userType;

        @SerializedName("company_id")
        @Expose
        private Integer companyId;

        @SerializedName("file_type")
        @Expose
        private String fileType;
        @SerializedName("attachment")
        @Expose
        private String attachment;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("file_path")
        @Expose
//    private Object filePath;
        private String filePath;

        @SerializedName("file_ext")
        @Expose
//    private Object fileExt;
        private String fileExt;

        @SerializedName("file_size")
        @Expose
//    private Object fileSize;
        private String fileSize;


        @SerializedName("status")
        @Expose
        private String status;


        @SerializedName("priority")
        @Expose
//    private Object priority;
        private String priority;

        @SerializedName("log_date_created")
        @Expose
        private String logDateCreated;

        @SerializedName("created_by")
        @Expose
//    private Object createdBy;
        private String createdBy;

        @SerializedName("log_date_modified")
        @Expose
//    private Object logDateModified;
        private String logDateModified;

        @SerializedName("modified_by")
        @Expose
//    private Object modifiedBy;
        private String modifiedBy;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
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

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileExt() {
            return fileExt;
        }

        public void setFileExt(String fileExt) {
            this.fileExt = fileExt;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
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
    }

    public class CompanyDetail {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("logo")
        @Expose
        private String logo;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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
