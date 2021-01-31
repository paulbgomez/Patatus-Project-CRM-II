package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.*;
import com.patatus.crmparte2.model.enums.Product;
import com.patatus.crmparte2.model.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SalesRepRepositoryTest {

    @Autowired
    SalesRepRepository salesRepRepository;
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    OpportunityRepository opportunityRepository;

    @BeforeEach
    public void setUp() {
        SalesRep salesRep1 = salesRepRepository.save(new SalesRep("Pepe"));
        SalesRep salesRep2 = salesRepRepository.save(new SalesRep("Juan"));

        Lead lead1 = leadRepository.save(new Lead("María", "916726410", "maria@transportesmaria.com", "Transportes María", salesRep1));
        Lead lead2 = leadRepository.save(new Lead("Antonio", "62913665", "antonio@antruck.com", "Antruck S.L.", salesRep1));
        Lead lead3 = leadRepository.save(new Lead("Sonia", "676208814", "sonia@wowpackages.com", "Wow Packages!", salesRep2));

        Contact contact1 = contactRepository.save(new Contact("María", "916726410", "maria@transportesmaria.com", "Transportes María"));
        Contact contact2 = contactRepository.save(new Contact("Antonio", "62913665", "antonio@antruck.com", "Antruck S.L."));
        Contact contact3 = contactRepository.save(new Contact("Sonia", "676208814", "sonia@wowpackages.com", "Wow Packages!"));

        Opportunity opportunity1 = opportunityRepository.save(new Opportunity(contact1, Product.BOX, 5, salesRep1));
        Opportunity opportunity2 = opportunityRepository.save(new Opportunity(contact2, Product.FLATBED, 4, salesRep1));
        Opportunity opportunity3 = new Opportunity(contact3, Product.HYBRID, 3, salesRep2);
        opportunity3.closeWon();
        opportunityRepository.save(opportunity3);
    }

    @AfterEach
    public void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    // General test to check if everything is working properly.
    @Test
    public void findAll() {
        // SalesRep:
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertEquals(2, salesRepList.size());
        assertEquals("Pepe", salesRepList.get(0).getName());
        // Leads:
        List<Lead> leadList = leadRepository.findAll();
        assertEquals(3, leadList.size());
        assertEquals("María", leadList.get(0).getName());
        // Opportunities:
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertEquals(3, opportunityList.size());
        assertEquals(5, opportunityList.get(0).getQuantity());
    }

    // To know what the following tests are checking, see SalesRepRepository class.
    @Test
    public void findLeadCountBySalesRep() {
        List<Object[]> leadCountBySalesRep = salesRepRepository.findLeadCountBySalesRep();
        assertEquals(2, leadCountBySalesRep.size());
        assertEquals(leadCountBySalesRep.get(0)[0],"Pepe");
        assertEquals(leadCountBySalesRep.get(0)[1],2L);
        assertEquals(leadCountBySalesRep.get(1)[0],"Juan");
        assertEquals(leadCountBySalesRep.get(1)[1],1L);
    }
    @Test
    public void findOpportunityCountBySalesRep() {
        List<Object[]> opportunityCountBySalesRep = salesRepRepository.findOpportunityCountBySalesRep();
        assertEquals(2, opportunityCountBySalesRep.size());
        assertEquals(opportunityCountBySalesRep.get(0)[0],"Pepe");
        assertEquals(opportunityCountBySalesRep.get(0)[1],2L);
        assertEquals(opportunityCountBySalesRep.get(1)[0],"Juan");
        assertEquals(opportunityCountBySalesRep.get(1)[1],1L);
    }
    @Test
    public void findOpportunityByStatusCountBySalesRep_CLOSEDWON_0() {
        List<Object[]> closedWonCountBySalesRep = salesRepRepository.findOpportunityByStatusCountBySalesRep(Status.CLOSED_WON);
        assertEquals(1, closedWonCountBySalesRep.size());
        assertEquals(closedWonCountBySalesRep.get(0)[0],"Juan");
        assertEquals(closedWonCountBySalesRep.get(0)[1],1L);
    }
    @Test
    public void findOpportunityByStatusCountBySalesRep_CLOSEDLOST_0() {
        List<Object[]> closedLostCountBySalesRep = salesRepRepository.findOpportunityByStatusCountBySalesRep(Status.CLOSED_LOST);
        assertEquals(0, closedLostCountBySalesRep.size());
    }
    @Test
    public void findOpportunityByStatusCountBySalesRep_OPEN_1() {
        List<Object[]> openCountBySalesRep = salesRepRepository.findOpportunityByStatusCountBySalesRep(Status.OPEN);
        assertEquals(1, openCountBySalesRep.size());
        assertEquals(openCountBySalesRep.get(0)[0], "Pepe");
        assertEquals(openCountBySalesRep.get(0)[1], 2L);
    }
}