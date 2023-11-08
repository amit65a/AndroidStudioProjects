package com.example.karobarapp.models;

import java.io.Serializable;

public class OthersDataModel implements Serializable {
    private String ido,mitio,telo,karobaro,parinamo,daro,kaifiyato;
    private boolean isChecked = false;



    public OthersDataModel(String ido, String mitio, String telo, String karobaro, String parinamo, String daro, String kaifiyato) {
        this.ido = ido;
        this.mitio = mitio;
        this.telo = telo;
        this.karobaro = karobaro;
        this.parinamo = parinamo;
        this.daro = daro;
        this.kaifiyato = kaifiyato;
    }
    public OthersDataModel() {
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getIdo() {
        return ido;
    }

    public void setIdo(String ido) {
        this.ido = ido;
    }

    public String getMitio() {
        return mitio;
    }

    public void setMitio(String mitio) {
        this.mitio = mitio;
    }

    public String getTelo() {
        return telo;
    }

    public void setTelo(String telo) {
        this.telo = telo;
    }

    public String getKarobaro() {
        return karobaro;
    }

    public void setKarobaro(String karobaro) {
        this.karobaro = karobaro;
    }

    public String getParinamo() {
        return parinamo;
    }

    public void setParinamo(String parinamo) {
        this.parinamo = parinamo;
    }

    public String getDaro() {
        return daro;
    }

    public void setDaro(String daro) {
        this.daro = daro;
    }

    public String getKaifiyato() {
        return kaifiyato;
    }

    public void setKaifiyato(String kaifiyato) {
        this.kaifiyato = kaifiyato;
    }
}
