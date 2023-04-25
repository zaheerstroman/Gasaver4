package com.gasaver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GraphReportsResponse {

    @SerializedName("graph_report")
    @Expose
    private String graphReport;
    @SerializedName("status_code")
    @Expose
    private Boolean statusCode;
    @SerializedName("message")
    @Expose
    private String message;

    public String getGraphReport() {
        return graphReport;
    }

    public void setGraphReport(String graphReport) {
        this.graphReport = graphReport;
    }

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
