package com.example.restservice;

import java.util.List;

public class JsonData {
    private String userName;
    private String timestamp;
    private String pdfTemplateName;

    public JsonData() {
    }

    public JsonData(String userName, String timestamp, String pdfTemplateName) {
        this.userName = userName;
        this.timestamp = timestamp;
        this.pdfTemplateName = pdfTemplateName;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPdfTemplateName() {
        return pdfTemplateName;
    }

    public void setPdfTemplateName(String pdfTemplateName) {
        this.pdfTemplateName = pdfTemplateName;
    }


    @Override
    public String toString() {
        return "JsonData{" +
                "userName='" + userName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", pdfTemplateName='" + pdfTemplateName + '\'' +
                '}';
    }

}
