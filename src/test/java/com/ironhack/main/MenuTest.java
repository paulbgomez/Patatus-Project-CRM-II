package com.ironhack.main;

import com.ironhack.main.menu.command.Command;
import com.ironhack.main.menu.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private static Menu menu;

    @BeforeAll
    public static void setUp() {
        menu = new Menu();
    }

    @Test
    void checkCommand_newLead_NEW_LEAD() {
        String input = "New Lead";
        assertEquals(Command.NEW_LEAD, menu.checkCommand(input));
    }

    @Test
    void checkCommand_newLeadWithoutSpace_UNKNOWN() {
        String input = "NewLead";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_newLeadWithMoreArgs_UNKNOWN() {
        String input = "New Lead 1";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLeads_SHOW_LEADS() {
        String input = "Show Leads";
        assertEquals(Command.SHOW_LEADS, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLead_UNKNOWN() {
        String input = "Show Lead";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLeadsWithoutSpace_UNKNOWN() {
        String input = "ShowLeads";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLeadsWithMoreArgs_UNKNOWN() {
        String input = "Show Leads 1";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadId_LOOKUP_LEAD() {
        String input = "Lookup Lead 2";
        assertEquals(Command.LOOKUP_LEAD, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadIdWithoutSpace_UNKNOWN() {
        String input = "LookupLead 2";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadWithMoreArgs_UNKNOWN() {
        String input = "Lookup Lead 2 4";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadWithoutId_UNKNOWN() {
        String input = "Lookup Lead";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_convertId_CONVERT_LEAD() {
        String input = "Convert 1";
        assertEquals(Command.CONVERT_LEAD, menu.checkCommand(input));
    }

    @Test
    void checkCommand_convertWithoutId_UNKNOWN() {
        String input = "Convert";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_convertWithMoreArgs_UNKNOWN() {
        String input = "Convert 2 4";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonId_CLOSE_WON_OPP() {
        String input = "close-won 2";
        assertEquals(Command.CLOSE_WON_OPP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonWithoutId_UNKNOWN() {
        String input = "close-won";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonWithMoreArgs_UNKNOWN() {
        String input = "close-won 2 4";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonWithoutHyphen_UNKNOWN() {
        String input = "close won 2";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostId_CLOSE_LOST_OPP() {
        String input = "close-lost 2";
        assertEquals(Command.CLOSE_LOST_OPP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostWithoutId_UNKNOWN() {
        String input = "close-lost";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostWithMoreArgs_UNKNOWN() {
        String input = "close-lost 2 4";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostWithoutHyphen_UNKNOWN() {
        String input = "close lost 2";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_randomCommand_UNKNOWN() {
        Random random = new Random();
        byte[] array = new byte[random.nextInt(10)];
        new Random().nextBytes(array);
        String input = new String(array);
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_emptyCommand_UNKNOWN() {
        String input = "";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_help_HELP() {
        String input = "help";
        assertEquals(Command.HELP, menu.checkCommand(input));
    }


    @Test
    void checkCommand_exit_EXIT() {
        String input = "exit";
        assertEquals(Command.EXIT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showAccounts_SHOW_ACCOUNTS() {
        String input = "show accounts";
        assertEquals(Command.SHOW_ACCOUNTS, menu.checkCommand(input));
    }


    @Test
    void checkCommand_showAccount_UNKNOWN() {
        String input = "show account";
        assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }


}