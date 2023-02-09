package com.example.angel1.Model;

public class ApplicationModel {
    String appTitle;
    String appDeadline;
    String passportUrl;
    String stdName;
    String stdage;
    String stdEmail;
    String stdSchool;

    //empty constructor for retrieving data from firebase
    public ApplicationModel() {
    }

    public ApplicationModel(String appTitle, String appDeadline, String passportUrl, String stdName, String stdage, String stdEmail, String stdSchool) {
        this.appTitle = appTitle;
        this.appDeadline = appDeadline;
        this.passportUrl = passportUrl;
        this.stdName = stdName;
        this.stdage = stdage;
        this.stdEmail = stdEmail;
        this.stdSchool = stdSchool;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppDeadline() {
        return appDeadline;
    }

    public void setAppDeadline(String appDeadline) {
        this.appDeadline = appDeadline;
    }

    public String getPassportUrl() {
        return passportUrl;
    }

    public void setPassportUrl(String passportUrl) {
        this.passportUrl = passportUrl;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdage() {
        return stdage;
    }

    public void setStdage(String stdage) {
        this.stdage = stdage;
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
