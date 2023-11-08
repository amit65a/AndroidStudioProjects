package com.example.karobar;

public class mainModel {
    String course,email,name,surl;

    mainModel(){}

    public mainModel(String course, String email, String name, String surl) {
        this.course = course;
        this.email = email;
        this.name = name;
        this.surl = surl;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}

