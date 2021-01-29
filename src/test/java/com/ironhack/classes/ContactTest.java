package com.ironhack.classes;

import com.ironhack.classes.Contact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    private static Contact contact1;

    @BeforeAll
    public static void setUp() {
        contact1 = new Contact("Juan", "+34916826184","juan@asinc.com", "As INC.");
    }

    @Test
    void setId_FirstContact_ID0(){
        assertEquals(0,contact1.getId());
    }

    @Test
    void generateID_NewContact_NewID(){
        Contact contact2 = new Contact("Pedro", "+34915256793", "pedro@asinc.com", "As INC.");
        assertEquals(1, contact2.getId());
    }

}