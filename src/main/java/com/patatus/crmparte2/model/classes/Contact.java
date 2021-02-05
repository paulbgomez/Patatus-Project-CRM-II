package com.patatus.crmparte2.model.classes;

import javax.persistence.*;

@Entity
public class Contact {
    // Properties:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    @ManyToOne
    private Account account;
    @OneToOne(mappedBy = "decisionMaker")
    private Opportunity opportunity;

    // Constructors:
    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email, String companyName, Account account) {
        setId();
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setAccount(account);
    }

    @Deprecated
    public Contact(String name, String phoneNumber, String email, String companyName) {
        setId();
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    // -----------------Methods------------------

    // Override of the toString() method to display the Contacts in a more friendly way.
    @Override
    public String toString() {
        return "ID-" + id +
                " | name: " + name +
                " | phoneNumber: " + phoneNumber +
                " | email: " + email +
                " | companyName: " + companyName;
    }

    // Getters & Setters:
    public int getId() {
        return id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }
}
