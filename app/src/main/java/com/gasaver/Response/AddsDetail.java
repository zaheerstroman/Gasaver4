package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

