package com.example.angel1.Model;

public class ApplicationModel {
    String userId;
    String passportUrl;
    String name;
    String age;
    String email;
    String school;
    String feePdf;

    //empty constructor for retrieving data from firebase
    public ApplicationModel() {
    }

    public ApplicationModel(String userId, String passportUrl, String name, String age, String email, String school, String feePdf) {
        this.userId = userId;
        this.passportUrl = passportUrl;
        this.name = name;
        this.age = age;
        this.email = email;
        this.school = school;
        this.feePdf = feePdf;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFeePdf() {
        return feePdf;
    }

    public void setFeePdf(String feePdf) {
        this.feePdf = feePdf;
    }
}
