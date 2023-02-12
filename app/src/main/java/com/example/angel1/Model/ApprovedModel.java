package com.example.angel1.Model;

public class ApprovedModel {

    private int id;
    String AppTitle;
    String stdName;
    String stdEmail;
    String stdSchool;
    String stdFee;
    String stdParents;
    String stdOrphan;
    String stdDisabilities;

    public ApprovedModel() {
    }

    public ApprovedModel(int id, String appTitle, String stdName, String stdEmail, String stdSchool, String stdFee, String stdParents, String stdOrphan, String stdDisabilities) {
        this.id = id;
        AppTitle = appTitle;
        this.stdName = stdName;
        this.stdEmail = stdEmail;
        this.stdSchool = stdSchool;
        this.stdFee = stdFee;
        this.stdParents = stdParents;
        this.stdOrphan = stdOrphan;
        this.stdDisabilities = stdDisabilities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStdDisabilities() {
        return stdDisabilities;
    }

    public void setStdDisabilities(String stdDisabilities) {
        this.stdDisabilities = stdDisabilities;
    }
}
