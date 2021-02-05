package com.patatus.crmparte2.controller;

import com.patatus.crmparte2.model.classes.*;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import com.patatus.crmparte2.model.enums.Status;
import com.patatus.crmparte2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.patatus.crmparte2.model.enums.Status.*;

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


    public String newSalesRep(String name) {
        SalesRep salesRep = new SalesRep(name);
        salesRep = salesRepRepository.save(salesRep);
        return ">> Added new SalesRep: " + salesRep;
    }

    // Method in which we ask the user for the necessary data to create a lead, and the we create a new lead.
    public String newLead(String name, String phoneNumber, String email, String companyName, SalesRep repLead) {

        Lead lead = new Lead(name, phoneNumber, email, companyName, repLead);
        lead = leadRepository.save(lead);

        return ">> Added new Lead: " + lead;
    }

    // Method to display all the information regarding a lead, indicating his id.
    public String lookupLead(int id) {
        if (checkIfExistsLead(id))
            return leadRepository.findById(id).toString();

        return printLeadNotFound(id);
    }

    // Method to display a list with all the existing leads in the database.
    public String showLeads() {
        return listToString(leadRepository.findAll());
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
        Optional<Opportunity> opportunity = opportunityRepository.findById(id);

        if (opportunity.isEmpty())
            return printOpportunityNotFound(id);

        opportunity.get().closeWon();
        opportunityRepository.save(opportunity.get());
        return opportunity.get().toString();
    }

    // With this method, we will close an opportunity as lost, indicating the id number.
    public String closeLostOpp(int id) {
        Optional<Opportunity> opportunity = opportunityRepository.findById(id);

        if (opportunity.isEmpty())
            return printOpportunityNotFound(id);

        opportunity.get().closeLost();
        opportunityRepository.save(opportunity.get());
        return opportunity.get().toString();
    }

    // Method to display the list of accounts that are registered in the database.
    public String showAccounts() {
        return listToString(accountRepository.findAll());
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

    // Method to display the values in a more user-friendly way
    public <T> String listToString(List<T> list) {
        return list.stream()
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

    public boolean checkIfExistsSalesRep() {
        return salesRepRepository.countSalesRep() > 0;
    }

    public Optional<SalesRep> findSalesRep(int n) {
        return salesRepRepository.findById(n);
    }

    public boolean checkIfExistsAnyAccount() {
        return accountRepository.countAccount() > 0;
    }

    public Optional<Account> findAccount(Integer id) {
        return accountRepository.findById(id);
    }

    // Lis MUST de previously ordered
    public Integer findMedian(List<Integer[]> objects){
        Integer median;
        int medianPosition = objects.size()/2;
        if(objects.size() % 2 != 0 ){
            median = (Integer) objects.get(medianPosition)[0];
        } else {
            Integer firstHalf = (Integer) objects.get((objects.size()/2)-1)[0];
            Integer secondHalf = (Integer) objects.get(medianPosition)[0];
            median = (firstHalf + secondHalf)/2;
        }
        return median;
    }



    // REPORTING:

    public String printTwoResults(List<Object[]> result){
        StringBuilder string = new StringBuilder();
        for (Object[] row : result){
            string.append(row[0].toString()).append(": ").append((row[1]).toString()).append("\n");
        }
        return string.toString();
    }


    public String showSalesReps() {
        List<SalesRep> salesReps = salesRepRepository.findAll();
        StringBuilder string = new StringBuilder();
        for (SalesRep salesRep : salesReps){
            string.append(salesRep.toString()).append("\n");
        }
        return string.toString();
    }

    public String findLeadCountBySalesRep() {
        List<Object[]> result = salesRepRepository.findLeadCountBySalesRep();
        return printTwoResults(result);
    }


    //OPPORTUNITIES BY PRODUCT
    public String findLeadByProduct() {
        List<Object[]> result = opportunityRepository.findOpportunitiesByProduct();
        return printTwoResults(result);
    }

    public String findLeadByProductStatusClosedWon() {
        List<Object[]> result = opportunityRepository.findByProductAndStatus(CLOSED_WON);
        return printTwoResults(result);
    }

    public String findLeadByProductStatusClosedLost() {
        List<Object[]> result = opportunityRepository.findByProductAndStatus(CLOSED_LOST);
        return printTwoResults(result);
    }

    public String findLeadByProductStatusOpen() {
        List<Object[]> result = opportunityRepository.findByProductAndStatus(OPEN);
        return printTwoResults(result);
    }

    //EMPLOYEE COUNTS
    public String findMaxEmployee() {
        List<Object[]> result = accountRepository.findMaxEmployeeCount();
        return printTwoResults(result);
    }

    public String findMinEmployee() {
        List<Object[]> result = accountRepository.findMinEmployeeCount();
        return printTwoResults(result);
    }

    public double findMeanEmployee() {
        return accountRepository.findMeanEmployeeCount();
    }

    public Integer findMedianEmployee () {
        List<Integer[]> result = accountRepository.findEmployeesByAccountOrdered();
        return findMedian(result);
    }
    public String findOpportunityCountBySalesRep() {
        List<Object[]> result = salesRepRepository.findOpportunityCountBySalesRep();
        return printTwoResults(result);
    }
    public String findOpportunityByStatusCountBySalesRep(Status status) {
        List<Object[]> result = salesRepRepository.findOpportunityByStatusCountBySalesRep(status);
        return printTwoResults(result);
    }

    public String findOpportunityCountByCity() {
        List<Object[]> result = opportunityRepository.findOpportunitiesByCity();
        return printTwoResults(result);
    }

    public String findClosedWonCountByCity() {
        List<Object[]> result = opportunityRepository.findByCityAndStatus(Status.CLOSED_WON);
        return printTwoResults(result);
    }

    public String findClosedLostCountByCity() {
        List<Object[]> result = opportunityRepository.findByCityAndStatus(Status.CLOSED_LOST);
        return printTwoResults(result);
    }

    public String findOpenCountByCity() {
        List<Object[]> result = opportunityRepository.findByCityAndStatus(Status.OPEN);
        return printTwoResults(result);
    }

    public String findMaxOpportunitiesByAccount(){
        List<Object[]> result = accountRepository.findMaxOpportunitiesByAccount();
        return printTwoResults(result);
    }

    public String findMinOpportunitiesByAccount(){
        List<Object[]> result = accountRepository.findMinOpportunitiesByAccount();
        return printTwoResults(result);
    }
    public String findOpportunityCountByCountry() {
        List<Object[]> result = opportunityRepository.findOpportunitiesByCountry();
        return printTwoResults(result);
    }

    public String findClosedWonCountByCountry() {
        List<Object[]> result = opportunityRepository.findByCountryAndStatus(Status.CLOSED_WON);
        return printTwoResults(result);
    }

    public String findClosedLostCountByCountry() {
        List<Object[]> result = opportunityRepository.findByCountryAndStatus(Status.CLOSED_LOST);
        return printTwoResults(result);
    }

    public String findOpenCountByCountry() {
        List<Object[]> result = opportunityRepository.findByCountryAndStatus(Status.OPEN);
        return printTwoResults(result);
    }


    public String findOpportunityCountByIndustry() {
        List<Object[]> result = opportunityRepository.findOpportunitiesByIndustry();
        return printTwoResults(result);
    }

    public String findOpportunityByStatusCountByIndustry(Status status) {
        List<Object[]> result = opportunityRepository.findByIndustryAndStatus(status);
        return printTwoResults(result);
    }
    public double findAvgOpportunitiesByAccount(){
        return accountRepository.findAvgOpportunitiesByAccount();
    }

    public Integer findMedianOpportunitiesByAccount() {
        return findMedian(accountRepository.findOpportunitiesByAccountOrdered());
    }

    public double findMeanQuantity() {
        return opportunityRepository.findAverageQuantityFromOpportunities();
    }

    public Integer findMedianQuantity() {
        List<Integer[]> result = opportunityRepository.orderOpportunities();
        return findMedian(result);
    }

    public Integer findMaxQuantity() {
        return opportunityRepository.findMaxQuantityFromOpportunities();
    }

    public Integer findMinQuantity() {
        return opportunityRepository.findMinQuantityFromOpportunities();
    }
}
