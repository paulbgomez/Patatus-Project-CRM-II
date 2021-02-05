package com.patatus.crmparte2.model;

import com.patatus.crmparte2.model.classes.Account;
import com.patatus.crmparte2.model.classes.Contact;
import com.patatus.crmparte2.model.classes.Opportunity;
import com.patatus.crmparte2.model.classes.SalesRep;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private static SalesRep salesRep;
    private static Account account;
    private static Contact contact;
    private static Opportunity opportunity;

    @BeforeAll
    public static void setUp() {
        salesRep = new SalesRep("Juan");
        account = new Account(Industry.PRODUCE, 3, "Madrid", "Spain");
        contact = new Contact("Pepe", "916726410", "as@as.com", "as", account);
        opportunity = new Opportunity(contact, Product.BOX, 2, salesRep, account);
    }

    @Test
    void addToContactList_NewContact_ThatContactOnList() {
        account.addToContactList(contact);
        assertEquals(1,account.getContactList().size());
    }

    @Test
    void addToOpportunityList_NewOpportunity_ThatOpportunityOnList() {
        account.addToOpportunityList(opportunity);
        assertEquals(1, account.getOpportunityList().size());
    }
}