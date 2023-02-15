package com.example.angel1.Model;

public class ConfirmedModel {
    String appTitle;
    String stdDeadline;
    String stdName;
    String stdEmail;
    String stdSchool;

    public ConfirmedModel() {
    }

    public ConfirmedModel(String appTitle, String stdDeadline, String stdName, String stdEmail, String stdSchool) {
        this.appTitle = appTitle;
        this.stdDeadline = stdDeadline;
        this.stdName = stdName;
        this.stdEmail = stdEmail;
        this.stdSchool = stdSchool;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getStdDeadline() {
        return stdDeadline;
    }

    public void setStdDeadline(String stdDeadline) {
        this.stdDeadline = stdDeadline;
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
