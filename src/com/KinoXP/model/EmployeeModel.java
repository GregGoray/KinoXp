package com.KinoXP.model;

public class EmployeeModel {

    private String userName;
    private String name;
    private String surname;
    private String email;
    private Integer phoneNumber;
    private String jobTitle;

    private String password;///////////////// BY MONICA - don't delete this before talking with me!!!

    //CONSTRUCTOR
    public EmployeeModel(){}


    public EmployeeModel(String userName, String password){ //////////BY MONICA - don't delete this before talking with me :)
        this.userName = userName;
        this.password = password;
    }
    public EmployeeModel(String userName, String name, String surname, String email,
                         Integer phoneNumber, String jobTitle) {
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
    }



    //GETTERS AND SETTERS
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    //////////////MONICA////////////////////////---------------------
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /////////////////////////////////////////////-------------------- Don't delete without talking with me! :)
}
