package com.example.angel1.Model;

public class ApprovedModel {
    String AppTitle;
    String stdName;
    String stdEmail;
    String stdSchool;

    public ApprovedModel() {
    }

    public ApprovedModel( String appTitle, String stdName, String stdEmail, String stdSchool) {
        AppTitle = appTitle;
        this.stdName = stdName;
        this.stdEmail = stdEmail;
        this.stdSchool = stdSchool;
    }
    public String getAppTitle() {
        return AppTitle;
    }

    public void setAppTitle(String appTitle) {
        AppTitle = appTitle;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdEmail() {
        return stdEmail;
    }

    public void setStdEmail(String stdEmail) {
        this.stdEmail = stdEmail;
    }

    public String getStdSchool() {
        return stdSchool;
    }

    public void setStdSchool(String stdSchool) {
        this.stdSchool = stdSchool;
    }
}
