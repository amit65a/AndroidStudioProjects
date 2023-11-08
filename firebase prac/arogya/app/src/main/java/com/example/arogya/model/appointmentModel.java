package com.example.arogya.model;

public class appointmentModel {
    private String id;
    private String date;
    private String drName;
    private String specialist;

    public appointmentModel(String id, String date, String drName, String specialist) {
        this.id = id;
        this.date = date;
        this.drName = drName;
        this.specialist = specialist;
    }

    public appointmentModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }
}
