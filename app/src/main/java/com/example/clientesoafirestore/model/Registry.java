package com.example.clientesoafirestore.model;

import java.io.Serializable;
import java.util.Date;

public class Registry implements Serializable {

    private int registryNumber, studentID, creditsNumber;
    private double ppa, price;
    private String studentCode, program;
    private String registryDate;

    public Registry(int registryNumber, String registryDate, int studentID, String studentCode, String program, int creditsNumber, double ppa, double price){
        this.registryNumber = registryNumber;
        this.studentID = studentID;
        this.creditsNumber = creditsNumber;
        this.ppa = ppa;
        this.price = price;
        this.studentCode = studentCode;
        this.program = program;
        this.registryDate = registryDate;
    }

    public Registry()
    {

    }

    public int getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(int registryNumber) {
        this.registryNumber = registryNumber;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCreditsNumber() {
        return creditsNumber;
    }

    public void setCreditsNumber(int creditsNumber) {
        this.creditsNumber = creditsNumber;
    }

    public double getPpa() {
        return ppa;
    }

    public void setPpa(double ppa) {
        this.ppa = ppa;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(String registryDate) {
        this.registryDate = registryDate;
    }
}
