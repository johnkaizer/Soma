package com.example.angel1.Model;

public class DetailsModel {

    String uAddress;
    String uHome;
    String uCounty;
    String uGender;
    String uName;
    String uEmail;

    public DetailsModel() {
    }

    public DetailsModel(String uAddress, String uHome, String uCounty, String uGender, String uName, String uEmail) {
        this.uAddress = uAddress;
        this.uHome = uHome;
        this.uCounty = uCounty;
        this.uGender = uGender;
        this.uName = uName;
        this.uEmail = uEmail;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuHome() {
        return uHome;
    }

    public void setuHome(String uHome) {
        this.uHome = uHome;
    }

    public String getuCounty() {
        return uCounty;
    }

    public void setuCounty(String uCounty) {
        this.uCounty = uCounty;
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }
}
