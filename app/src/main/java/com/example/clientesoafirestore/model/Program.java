package com.example.clientesoafirestore.model;

import java.io.Serializable;

public class Program implements Serializable {

    private  String name, verification, modality,programCode;
    private int duration;

    public Program(String name, String verification, int duration, String programCode, String modality) {
        this.name = name;
        this.verification = verification;
        this.duration = duration;
        this.programCode = programCode;
        this.modality = modality;
    }

    public Program(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }
}
