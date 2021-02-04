package com.patatus.crmparte2.controller;

import com.patatus.crmparte2.model.classes.*;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import com.patatus.crmparte2.repository.AccountRepository;
import com.patatus.crmparte2.repository.ContactRepository;
import com.patatus.crmparte2.repository.OpportunityRepository;
import com.patatus.crmparte2.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Controller {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    SalesRepRepository salesRepRepository;
    @Autowired
    ContactRepository contactRepository;

    private final Map<Integer, Lead> leadMap = new HashMap<>();
    private final Map<Integer, Account> accountMap = new HashMap<>();
    private final Map<Integer, Opportunity> opportunityMap = new HashMap<>();

    // Method in which we ask the user for the necessary data to create a lead, and the we create a new lead.
    public String newLead(String name, String phoneNumber, String email, String companyName) {
        //TODO: crear bien el salesRep y usar el constructor nuevo
        Lead lead = new Lead(name, phoneNumber, email, companyName);
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
    // from the lead list, and an account is created in its place. This account includes the
    // information of the lead converted into a contact, and an opportunity with the information
    // regarding the possible sale.
    public String convertLead(int id, Product product, int quantity, Industry industry, int employeeCount, String city, String country) {
        if (!checkIfExistsLead(id))
            return printLeadNotFound(id);

        // Get the Lead object to convert
        Lead leadConvert = leadMap.get(id);
        SalesRep salesRep = salesRepRepository.save(leadConvert.getRepLead());

        // Create new account with all data required
        Account account = new Account(industry, employeeCount, city, country);
        account = accountRepository.save(account);

        // Create a Contact object based on the Lead data
        Contact decisionMaker = createContact(leadConvert);
        decisionMaker = contactRepository.save(decisionMaker);
        // Create an Opportunity with the new Contact and some data
        Opportunity opportunity = new Opportunity(decisionMaker, product, quantity, salesRep, account);
        opportunityRepository.save(opportunity);

        // Save the new objects
        //TODO: Luego se quita
        accountMap.put(account.getId(), account);
        opportunityMap.put(opportunity.getId(), opportunity);
        leadMap.remove(id);

        return ">> Added new Account: " + account + "\n" +
               "<< Removed Lead: " + leadConvert;
    }

    // Method to create a contact with the information that had been registered in the lead that has been converted
    private Contact createContact(Lead lead) {
        return new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
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
    public boolean checkIfExistsLead(int id) {
        return leadMap.containsKey(id);
    }

    // Method to check if there is an existing Opportunity with this id
    public boolean checkIfExistsOpportunity(int id) {
        return opportunityMap.containsKey(id);
    }
}
