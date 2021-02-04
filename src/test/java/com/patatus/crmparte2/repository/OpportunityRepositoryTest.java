package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.*;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import com.patatus.crmparte2.model.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityRepositoryTest {

    @Autowired
    SalesRepRepository salesRepRepository;
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    AccountRepository accountRepository;


    @BeforeEach
    public void setUp() {
        List<SalesRep> salesRepList = salesRepRepository.saveAll(List.of(
                new SalesRep("Pepe"),
                new SalesRep("Juan"),
                new SalesRep("Antonio")
        ));

        List<Lead> leadList = leadRepository.saveAll(List.of(
                new Lead("bla", "916726410", "bla@wuw.com", "wuw", salesRepList.get(0)),
                new Lead("ble", "62913665", "ble@wow.com", "wow", salesRepList.get(1)),
                new Lead("bli", "676208814", "bli@wiw.com", "wiw", salesRepList.get(2))
        ));

        Account account = new Account(Industry.ECOMMERCE, 20, "Detroit", "USA");
        accountRepository.save(account);

        List<Contact> contacts = contactRepository.saveAll(List.of(
                new Contact("bla", "916726410", "bla@wuw.com", "wuw"),
                new Contact("bla", "916726410", "bla@wuw.com", "wuw"),
                new Contact("bla", "916726410", "bla@wuw.com", "wuw"),
                new Contact("bla", "916726410", "bla@wuw.com", "wuw"),
                new Contact("bla", "916726410", "bla@wuw.com", "wuw")
        ));

        List<Opportunity> opportunityList = opportunityRepository.saveAll(List.of(
                new Opportunity(contacts.get(0), Product.BOX, 10, salesRepList.get(0), account),
                new Opportunity(contacts.get(1), Product.FLATBED, 100, salesRepList.get(1), account),
                new Opportunity(contacts.get(2), Product.FLATBED, 70, salesRepList.get(1), account),
                new Opportunity(contacts.get(3), Product.FLATBED, 50, salesRepList.get(2), account),
                new Opportunity(contacts.get(4), Product.FLATBED, 70, salesRepList.get(2), account)
        ));

    }

    @AfterEach
    public void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @Test
    public void findAll(){
        List<Opportunity> opportunityOptional = opportunityRepository.findAll();
        assertEquals(5, opportunityOptional.size());
    }

    @Test
    public void findMaxQuantityByRepContainingName(){
        List<Object[]> opportunityOptional = opportunityRepository.findMaxQuantityByRepContainingName();
        assertEquals(100, opportunityOptional.get(0)[1]);
    }


    @Test
    public void findMaxQuantity_OpportunityList_MaxQuantity(){
        Integer opportunityOptional = opportunityRepository.findMaxQuantityFromOpportunities();
        assertEquals(100, opportunityOptional);
    }


    @Test
    public void findMaxQuantity_OpportunityWonList_MaxQuantity(){
        Integer opportunityOptional = opportunityRepository.findMaxQuantityFromWonOpportunities(Status.OPEN);
        assertEquals(100, opportunityOptional);
    }

    @Test
    public void findMinQuantity_OpportunityList_MaxQuantity(){
        Integer opportunityOptional = opportunityRepository.findMinQuantityFromOpportunities();
        assertEquals(10, opportunityOptional);
    }

    @Test
    public void findMinQuantity_OpportunityWonList_MaxQuantity(){
        Integer opportunityOptional = opportunityRepository.findMinQuantityFromWonOpportunities(Status.OPEN);
        assertEquals(10, opportunityOptional);
    }

    @Test
    public void findMinQuantityByRepContainingName(){
        List<Object[]> opportunityOptional = opportunityRepository.findMinQuantityByRepContainingName();
        assertEquals(10, opportunityOptional.get(0)[1]);
    }

    @Test
    public void findMedianFromOpportunities(){
        Integer median = opportunityRepository.findMedianFromOpportunities();
        assertEquals(70, median);
    }

}