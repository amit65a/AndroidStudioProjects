package com.example.karobarapp.models;

import java.io.Serializable;

public class KharidDataModel implements Serializable{
    private String idk,mitik,telk,batak,parinamk,dark,kaifiyatk;
    private boolean isChecked = false;
    public KharidDataModel(String idk, String mitik, String telk, String batak, String parinamk, String dark, String kaifiyatk) {
        this.idk = idk;
        this.mitik = mitik;
        this.telk = telk;
        this.batak = batak;
        this.parinamk = parinamk;
        this.dark = dark;
        this.kaifiyatk = kaifiyatk;
    }

    public KharidDataModel() {
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getIdk() {
        return idk;
    }

    public void setIdk(String idk) {
        this.idk = idk;
    }

    public String getMitik() {
        return mitik;
    }

    public void setMitik(String mitik) {
        this.mitik = mitik;
    }

    public String getTelk() {
        return telk;
    }

    public void setTelk(String telk) {
        this.telk = telk;
    }

    public String getBatak() {
        return batak;
    }

    public void setBatak(String batak) {
        this.batak = batak;
    }

    public String getParinamk() {
        return parinamk;
    }

    public void setParinamk(String parinamk) {
        this.parinamk = parinamk;
    }

    public String getDark() {
        return dark;
    }

    public void setDark(String dark) {
        this.dark = dark;
    }

    public String getKaifiyatk() {
        return kaifiyatk;
    }

    public void setKaifiyatk(String kaifiyatk) {
        this.kaifiyatk = kaifiyatk;
    }
}
