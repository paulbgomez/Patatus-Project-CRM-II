package com.patatus.crmparte2.model.classes;

import com.patatus.crmparte2.menu.MenuColors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "leads")
public class Lead {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    @ManyToOne
    private SalesRep repLead;

    // Constructors:
    public Lead() {
    }

    public Lead(String name, String phoneNumber, String email, String companyName, SalesRep repLead) {
        setId();
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setRepLead(repLead);
    }

    @Deprecated
    public Lead(String name, String phoneNumber, String email, String companyName) {
        setId();
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setRepLead(new SalesRep("Ejemplo ejemplo ejemplo"));
    }

    // -----------------Methods------------------

    // Override of the toString() method to display the Leads in a more friendly way.
    @Override
    public String toString() {
        return "ID-" + id +
                " | name: " + name +
                " | phoneNumber: " + phoneNumber +
                " | email: " + email +
                " | companyName: " + companyName +
                MenuColors.setColorRed(" <-> ") + "SalesRep ID-" + getRepLead().getId() +
                " | name: " + getRepLead().getName();
    }

    //Getters & Setters
    public int getId() {
        return this.id;
    }

    public void setId() {
        this.id = id;
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

    public SalesRep getRepLead() {
        return repLead;
    }

    public void setRepLead(SalesRep repLead) {
        this.repLead = repLead;
    }
}

