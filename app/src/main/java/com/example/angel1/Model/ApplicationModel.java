package com.example.angel1.Model;

public class ApplicationModel {
    String appTitle;
    String appDeadline;
    String userId;
    String passportUrl;
    String stdName;
    String stdage;
    String stdEmail;
    String stdSchool;
    String stdFee;
    String stdParents;
    String stdOrphan;
    String stdSiblings;
    String stdDisabilities;

    //empty constructor for retrieving data from firebase
    public ApplicationModel() {
    }

    public ApplicationModel(String appTitle, String appDeadline, String userId, String passportUrl, String stdName, String stdage, String stdEmail, String stdSchool, String stdFee, String stdParents, String stdOrphan, String stdSiblings, String stdDisabilities) {
        this.appTitle = appTitle;
        this.appDeadline = appDeadline;
        this.userId = userId;
        this.passportUrl = passportUrl;
        this.stdName = stdName;
        this.stdage = stdage;
        this.stdEmail = stdEmail;
        this.stdSchool = stdSchool;
        this.stdFee = stdFee;
        this.stdParents = stdParents;
        this.stdOrphan = stdOrphan;
        this.stdSiblings = stdSiblings;
        this.stdDisabilities = stdDisabilities;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getStdFee() {
        return stdFee;
    }

    public void setStdFee(String stdFee) {
        this.stdFee = stdFee;
    }

    public String getStdParents() {
        return stdParents;
    }

    public void setStdParents(String stdParents) {
        this.stdParents = stdParents;
    }

    public String getStdOrphan() {
        return stdOrphan;
    }

    public void setStdOrphan(String stdOrphan) {
        this.stdOrphan = stdOrphan;
    }

    public String getStdSiblings() {
        return stdSiblings;
    }

    public void setStdSiblings(String stdSiblings) {
        this.stdSiblings = stdSiblings;
    }

    public String getStdDisabilities() {
        return stdDisabilities;
    }

    public void setStdDisabilities(String stdDisabilities) {
        this.stdDisabilities = stdDisabilities;
    }
}
