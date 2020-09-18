package com.example.clientesoafirestore.model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private String name, code, email, gender, celphone;
    private int id;
    private String dateOfBirth;

    public Student(String name, String code, String email, String gender, int id, String celphone, String dateOfBirth) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.gender = gender;
        this.id = id;
        this.celphone = celphone;
        this.dateOfBirth = dateOfBirth;
    }

    public  Student(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCelphone() {
        return celphone;
    }

    public void setCelphone(String celphone) {
        this.celphone = celphone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
