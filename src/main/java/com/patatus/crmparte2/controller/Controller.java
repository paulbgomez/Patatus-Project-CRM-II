package com.patatus.crmparte2.controller;

import com.patatus.crmparte2.model.classes.*;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import com.patatus.crmparte2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Controller {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    SalesRepRepository salesRepRepository;


    private final Map<Integer, Lead> leadMap = new HashMap<>();
    private final Map<Integer, Account> accountMap = new HashMap<>();
    private final Map<Integer, Opportunity> opportunityMap = new HashMap<>();

    public String newSalesRep(String name) {
        SalesRep salesRep = new SalesRep(name);
        salesRep = salesRepRepository.save(salesRep);
        return ">> Added new SalesRep: " + salesRep;
    }

    // Method in which we ask the user for the necessary data to create a lead, and the we create a new lead.
    public String newLead(String name, String phoneNumber, String email, String companyName, SalesRep repLead) {

        Lead lead = new Lead(name, phoneNumber, email, companyName, repLead);
        lead = leadRepository.save(lead);
        leadMap.put(lead.getId(), lead);

        return ">> Added new Lead: " + lead;
    }

    // Method to display all the information regarding a lead, indicating his id.
    public String lookupLead(int id) {
        if (checkIfExistsLead(id))
            return leadMap.get(id).toString();

        return printLeadNotFound(id);
    }

    // Method to display a list with all the existing leads in the database.
    public String showLeads() {
        return mapValuesToString(leadMap);
    }

    // Method to convert a lead into an opportunity. When this happens, the lead disappears
    // from the lead list.
    public String convertLead(int id, Product product, int quantity, Account account) {
        // Get the Lead object to convert
        Optional<Lead> leadFound = leadRepository.findById(id);
        if (leadFound.isEmpty())
            return printLeadNotFound(id);
        Lead leadConvert = leadFound.get();

        SalesRep salesRep = leadConvert.getRepLead();

        // Create and save a Contact object based on the Lead data
        Contact decisionMaker = createContact(leadConvert, account);
        decisionMaker = contactRepository.save(decisionMaker);

        // Create and save an Opportunity with the new Contact and some data
        Opportunity opportunity = new Opportunity(decisionMaker, product, quantity, salesRep, account);
        opportunityRepository.save(opportunity);

        return ">> Created Opportunity: " + opportunity + "\n" +
                "<< Removed Lead: " + leadConvert;
    }

    public Account createAccount(Industry industry, int employeeCount, String city, String country) {
        Account account = new Account(industry, employeeCount, city, country);
        return accountRepository.save(account);
    }

    // Method to create a contact with the information that had been registered in the lead that has been converted
    private Contact createContact(Lead lead, Account account) {
        return new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName(), account);
    }

    // Method to create an opportunity: when a lead is converted to an opportunity,
    // the user will be asked for the information regarding the possible sale.
    @Deprecated
    private Opportunity createOpportunity(Contact decisionMaker, SalesRep salesRep, Product product, int quantity, Account account) {

        Opportunity opportunity = new Opportunity(decisionMaker, product, quantity, salesRep, account);
        opportunityMap.put(opportunity.getId(), opportunity);
        return opportunity;
    }

    // Method to create an account: when a lead becomes an opportunity, an account is generated that
    // includes your contact (with the information that we had saved in your lead), and the information
    // regarding the possible sale, which will be asked to the user.
    private Account createAccount(Contact contact, Opportunity opportunity, Industry industry, int employeeCount, String city, String country) {
        Account account = new Account(industry, employeeCount, city, country);
        account.addToContactList(contact);
        account.addToOpportunityList(opportunity);

        return account;
    }

    // With this method, we will close an opportunity as successful, indicating the id number.
    public String closeWonOpp(int id) {
        if(!checkIfExistsOpportunity(id))
            return printOpportunityNotFound(id);

        Opportunity opportunity = opportunityMap.get(id);
        opportunity.closeWon();
        return opportunity.toString();
    }

    // With this method, we will close an opportunity as lost, indicating the id number.
    public String closeLostOpp(int id) {
        if(!checkIfExistsOpportunity(id))
            return printOpportunityNotFound(id);

        Opportunity opportunity = opportunityMap.get(id);
        opportunity.closeLost();
        return opportunity.toString();
    }

    // Method to display the list of accounts that are registered in the database.
    public String showAccounts() {
        return mapValuesToString(accountMap);
    }

    // Method that returns an error message when the Lead with id does not exist.
    public String printLeadNotFound(int id) {
        return "Lead with id=" + id + " not found.";
    }

    // Method that returns an error message when the Opportunity with id that does not exist.
    public String printOpportunityNotFound(int id) {
        return "Opportunity with id=" + id + " not found.";
    }

    // Method to display the values in a more user-friendly way
    public <T, T2> String mapValuesToString(Map<T2, T> map) {
        return map.values().stream()
                .map(T::toString)
                .collect(Collectors.joining("\n"));
    }

    // Method to check if there is an existing Lead with this id
    public boolean checkIfExistsLead(Integer id) {
        return leadRepository.existsById(id);
    }

    // Method to check if there is an existing Opportunity with this id
    public boolean checkIfExistsOpportunity(Integer id) {
        return opportunityRepository.existsById(id);
    }

    //TODO fix this
    public boolean checkIfExistsSalesRep() {
        return salesRepRepository.exists(Example.of(new SalesRep()));
    }

    public Optional<SalesRep> findSalesRep(int n) {
        return salesRepRepository.findById(n);
    }

    //TODO fix this
    public boolean checkIfExistsAnyAccount() {
        return accountRepository.countAccount() > 0;
    }

    public Optional<Account> findAccount(Integer id) {
        return accountRepository.findById(id);
    }
}
