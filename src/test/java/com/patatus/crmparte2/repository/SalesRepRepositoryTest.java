package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.*;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SalesRepRepositoryTest {

    /* He hecho estos tests para comprobar que la DB va funcionando. He probado a obtener un SalesRep por lead,
    por producto de una opportunity etc.
    No son test que tengan que quedar en el futuro, pero han servido para comprobar el funcionamiento.
    Falta añadir Account a toda esta mierda, modificando también el constructor de Opportunity. */


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
        SalesRep salesRep1 = salesRepRepository.save(new SalesRep("Pepe"));
        SalesRep salesRep2 = salesRepRepository.save(new SalesRep("Juan"));

        Lead lead1 = leadRepository.save(new Lead("bla", "916726410", "bla@wuw.com", "wuw", salesRep1));
        Lead lead2 = leadRepository.save(new Lead("ble", "62913665", "ble@wow.com", "wow", salesRep1));
        Lead lead3 = leadRepository.save(new Lead("bli", "676208814", "bli@wiw.com", "wiw", salesRep2));

        Contact contact1 = contactRepository.save(new Contact("bla", "916726410", "bla@wuw.com", "wuw"));

        Opportunity opportunity1 = opportunityRepository.save(new Opportunity(contact1, Product.BOX, 10, salesRep1));
        Account account1 = accountRepository.save(new Account(Industry.PRODUCE, 10, "Madrid", "Europe", List.of(contact1), List.of(opportunity1)));

        // ESTO FURULA EN EL TEST PERO HAY QUE REVISAR LAS RELACIONES.
//        Account account1 = accountRepository.save(new Account(Industry.PRODUCE, 10, "Madrid", "Europe"));
//        Opportunity opportunity1 = opportunityRepository.save(new Opportunity(contact1, Product.BOX, 10, salesRep1, account1));



        /* TODO: Esto de añadir así leads y oportunidades a las listas de los ManyToOne, habría que pensarlo para la app en producción:
            Es decir, en realidad al crear un lead habría que mandar ese lead a la lista de su salesRep.
        */
        salesRep1.setRepLead(List.of(lead1, lead2));
        salesRep2.setRepLead(List.of(lead3));
        salesRep1.setRepOpportunity(List.of(opportunity1));
    }

    @AfterEach
    public void tearDown() {
        accountRepository.deleteAll();
        opportunityRepository.deleteAll();
        // accountRepository.deleteAll(); si metemos el account en el constructor del opportunity, hace falta que este delete vaya después del de opportunity.
        contactRepository.deleteAll();
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @Test
    public void findAll() {
        // SalesRep:
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        assertEquals(2, salesRepList.size());
        assertEquals("Pepe", salesRepList.get(0).getName());
        // Leads:
        List<Lead> leadList = leadRepository.findAll();
        assertEquals(3, leadList.size());
        assertEquals("bla", leadList.get(0).getName());
        // Opportunities:
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertEquals(1, opportunityList.size());
        assertEquals(10, opportunityList.get(0).getQuantity());
        // Accounts:
//        List<Account> accountList = accountRepository.findAll();
//        assertEquals(1, accountList.size());
//        assertEquals(Industry.PRODUCE, accountList.get(0).getIndustry());

    }

    @Test
    public void findSalesRepWithLeadsByName_ValidSalesRepName_RightSalesRepWithLeads() {
        Optional<SalesRep> salesRep = salesRepRepository.findSalesRepWithLeadsByName("Pepe");
        if (salesRep.isPresent()) {
            assertEquals("bla", salesRep.get().getRepLead().get(0).getName());
        } else {
            fail("not present");
        }
    }

    @Test
    public void findSalesRepByLeadName_ValidLeadName_RightSalesRep(){
        Optional<SalesRep> salesRep = salesRepRepository.findSalesRepByLeadName("bli");
        if (salesRep.isPresent()) {
            assertEquals("Juan", salesRep.get().getName());
        } else {
            fail("not present");
        }
    }

    @Test
    public void findSalesRepByProduct_ValidProduct_RightSalesRep() {
        Optional<SalesRep> salesRep = salesRepRepository.findSalesRepByProduct(Product.BOX);
        if (salesRep.isPresent()) {
            assertEquals("Pepe", salesRep.get().getName());
        } else {
            fail("not present");
        }
    }

//    @Test
//    public void findSalesRepByCityOfAccount_ValidCity_RightSalesRep(){
//        Optional<List<SalesRep>> salesRep = salesRepRepository.findSalesRepByCityOfAccount("Madrid");
//        if (salesRep.isPresent()) {
//            assertEquals("Pepe", salesRep.get().get(0).getName());
//            System.out.println(salesRep.get().get(0).getName() + " tiene un account con ciudad " + salesRep.get().get(0).getRepOpportunity().get(0).getAccount().getCity());
//        } else {
//            fail("not present");
//        }
//    }
}