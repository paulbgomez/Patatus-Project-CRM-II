package com.ironhack.classes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeadTest {

    private static Lead lead1;

    @BeforeAll
    public static void setUp() {
        lead1 = new Lead("Paco", "+34916826184","paco@porr.as", "ACME");
    }

    @Test
    void setId_FirstContact_ID0(){
        assertEquals(0,lead1.getId());
    }

    @Test
    void generateID_NewContact_NewID(){
        Lead lead2 = new Lead("Manolo", "+34915256793", "manolin@manol.es", "Pocero S.L.");
        assertEquals(1, lead2.getId());
    }
}