package com.patatus.crmparte2.model.classes;
import com.patatus.crmparte2.model.enums.Industry;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Account {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    @Fetch(FetchMode.SUBSELECT)
    private List<Contact> contactList;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "account")
    @Fetch(FetchMode.SUBSELECT)
    private List<Opportunity> opportunityList;

    // This is for the incremented self-generated id:
    private static int idGenerator = 0;

    // Constructor

    public Account() {
    }

    public Account(Industry industry, int employeeCount, String city, String country) {
//        setId();
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        // Esto se quitará cuando no tengamos nada que cuegue de las listas
        setContactList(new ArrayList<>());
        setOpportunityList(new ArrayList<>());
    }

    @Deprecated
    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList, List<Opportunity> opportunityList) {
        setId();
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        setContactList(contactList);
        setOpportunityList(opportunityList);
    }

    // -----------------Methods------------------

    // Method to add a contact to a contact list.
    public void addToContactList(Contact contact){
        getContactList().add(contact);
    }

    // Method to add an opportunity to an opportunity list.
    public void addToOpportunityList(Opportunity opportunity){
        getOpportunityList().add(opportunity);
    }

    // Override of the toString() method to display the Accounts in a more friendly way.
    @Override
    public String toString() {
        return "\n   ID-" + id +
                " | industry: " + industry +
                " | employeeCount: " + employeeCount +
                " | city: " + city +
                " | country: " + country +
                "\n   List of Contacts:" +
                "\n      " + contactList.stream()
                .map(Contact::toString)
                .collect(Collectors.joining("\n")) +
                "\n   List of Opportunities:" +
                "\n      " + opportunityList.stream()
                .map(Opportunity::toString)
                .collect(Collectors.joining("\n"));
    }

    // Getters & Setters:
    public int getId() {
        return id;
    }

    public void setId() {
        this.id = generateId();
    }

    public static int generateId(){
        return idGenerator++;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

}
