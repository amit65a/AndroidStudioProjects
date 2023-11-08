package com.example.karobarapp.models;

import java.io.Serializable;

public class MBCDataModel implements Serializable {
    private String id;
    private String miti;
    private String gadino;
    private String tel;
    private String parinam;
    private String dar;
    private String kaifiyat;
    private boolean isChecked = false;

    public MBCDataModel(String id, String miti, String gadino, String tel, String parinam, String dar, String kaifiyat) {
        this.id = id;
        this.miti = miti;
        this.gadino = gadino;
        this.tel = tel;
        this.parinam = parinam;
        this.dar = dar;
        this.kaifiyat = kaifiyat;
    }

    public MBCDataModel() {
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMiti() {
        return miti;
    }

    public void setMiti(String miti) {
        this.miti = miti;
    }

    public String getGadino() {
        return gadino;
    }

    public void setGadino(String gadino) {
        this.gadino = gadino;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getParinam() {
        return parinam;
    }

    public void setParinam(String parinam) {
        this.parinam = parinam;
    }

    public String getDar() {
        return dar;
    }

    public void setDar(String dar) {
        this.dar = dar;
    }

    public String getKaifiyat() {
        return kaifiyat;
    }

    public void setKaifiyat(String kaifiyat) {
        this.kaifiyat = kaifiyat;
    }
}
