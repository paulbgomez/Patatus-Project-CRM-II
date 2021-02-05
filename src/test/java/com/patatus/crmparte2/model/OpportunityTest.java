package com.patatus.crmparte2.model;

import com.patatus.crmparte2.model.classes.Account;
import com.patatus.crmparte2.model.classes.Contact;
import com.patatus.crmparte2.model.classes.Opportunity;
import com.patatus.crmparte2.model.classes.SalesRep;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import com.patatus.crmparte2.model.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityTest {
    static Opportunity opportunity;

    @BeforeAll
    static void beforeAll() {
        SalesRep salesrep = new SalesRep("Pepe");
        Account account = new Account(Industry.PRODUCE, 20, "Madrid", "Spain");
        Contact contact = new Contact("Juan", "+34916826184","juan@asinc.com", "As INC.", account);
        opportunity = new Opportunity(contact, Product.FLATBED, 2, salesrep, account);
    }

    @Test
    void closeWon_opportunityObject_statusChangedToWon() {
        opportunity.closeWon();
        assertEquals(Status.CLOSED_WON, opportunity.getStatus());
    }

    @Test
    void closeLost_opportunityObject_statusChangedToLost() {
        opportunity.closeLost();
        assertEquals(Status.CLOSED_LOST, opportunity.getStatus());
    }

}