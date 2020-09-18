package com.example.clientesoafirestore.model;

public class Program {

    private  String name, verification, duration;
    private int programCode, modality;

    public Program(String name, String verification, String duration, int programCode, int modality) {
        this.name = name;
        this.verification = verification;
        this.duration = duration;
        this.programCode = programCode;
        this.modality = modality;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getProgramCode() {
        return programCode;
    }

    public void setProgramCode(int programCode) {
        this.programCode = programCode;
    }

    public int getModality() {
        return modality;
    }

    public void setModality(int modality) {
        this.modality = modality;
    }
}
