package com.example.clientesoafirestore.model;

import java.util.Date;

public class Student {

    private String name, code, email, gender;
    private int id, celphone;
    private Date dateOfBirth;

    public Student(String name, String code, String email, String gender, int id, int celphone, Date dateOfBirth) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.gender = gender;
        this.id = id;
        this.celphone = celphone;
        this.dateOfBirth = dateOfBirth;
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

    public int getCelphone() {
        return celphone;
    }

    public void setCelphone(int celphone) {
        this.celphone = celphone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
