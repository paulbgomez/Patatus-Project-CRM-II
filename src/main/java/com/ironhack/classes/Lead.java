package com.ironhack.classes;

public class Lead {
    // Properties
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    // To generate autoincrementID:
    private static int idGenerator = 0;

    // Constructor
    public Lead(String name, String phoneNumber, String email, String companyName) {
        setId();
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    // -----------------Methods------------------

    // Override of the toString() method to display the Leads in a more friendly way.
    @Override
    public String toString() {
        return "ID-" + id +
                " | name: " + name +
                " | phoneNumber: " + phoneNumber +
                " | email: " + email +
                " | companyName: " + companyName;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId() {
        this.id = generateId();
    }

    public static int generateId()  {
        return idGenerator++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

