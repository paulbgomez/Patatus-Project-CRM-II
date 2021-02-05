package com.patatus.crmparte2.menu;

import com.patatus.crmparte2.menu.command.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

class MenuTest {

    private static Menu menu;

    @BeforeAll
    public static void setUp() {
        menu = new Menu();
    }

    @Test
    void checkCommand_newLead_NEW_LEAD() {
        String input = "New Lead";
        Assertions.assertEquals(Command.NEW_LEAD, menu.checkCommand(input));
    }

    @Test
    void checkCommand_newLeadWithoutSpace_UNKNOWN() {
        String input = "NewLead";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_newLeadWithMoreArgs_UNKNOWN() {
        String input = "New Lead 1";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLeads_SHOW_LEADS() {
        String input = "Show Leads";
        Assertions.assertEquals(Command.SHOW_LEADS, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLead_UNKNOWN() {
        String input = "Show Lead";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLeadsWithoutSpace_UNKNOWN() {
        String input = "ShowLeads";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showLeadsWithMoreArgs_UNKNOWN() {
        String input = "Show Leads 1";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadId_LOOKUP_LEAD() {
        String input = "Lookup Lead 2";
        Assertions.assertEquals(Command.LOOKUP_LEAD, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadIdWithoutSpace_UNKNOWN() {
        String input = "LookupLead 2";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadWithMoreArgs_UNKNOWN() {
        String input = "Lookup Lead 2 4";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_lookupLeadWithoutId_UNKNOWN() {
        String input = "Lookup Lead";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_convertId_CONVERT_LEAD() {
        String input = "Convert 1";
        Assertions.assertEquals(Command.CONVERT_LEAD, menu.checkCommand(input));
    }

    @Test
    void checkCommand_convertWithoutId_UNKNOWN() {
        String input = "Convert";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_convertWithMoreArgs_UNKNOWN() {
        String input = "Convert 2 4";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonId_CLOSE_WON_OPP() {
        String input = "close-won 2";
        Assertions.assertEquals(Command.CLOSE_WON_OPP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonWithoutId_UNKNOWN() {
        String input = "close-won";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonWithMoreArgs_UNKNOWN() {
        String input = "close-won 2 4";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeWonWithoutHyphen_UNKNOWN() {
        String input = "close won 2";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostId_CLOSE_LOST_OPP() {
        String input = "close-lost 2";
        Assertions.assertEquals(Command.CLOSE_LOST_OPP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostWithoutId_UNKNOWN() {
        String input = "close-lost";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostWithMoreArgs_UNKNOWN() {
        String input = "close-lost 2 4";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_closeLostWithoutHyphen_UNKNOWN() {
        String input = "close lost 2";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_randomCommand_UNKNOWN() {
        Random random = new Random();
        byte[] array = new byte[random.nextInt(10)];
        new Random().nextBytes(array);
        String input = new String(array);
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_emptyCommand_UNKNOWN() {
        String input = "";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_help_HELP() {
        String input = "help";
        Assertions.assertEquals(Command.HELP, menu.checkCommand(input));
    }


    @Test
    void checkCommand_exit_EXIT() {
        String input = "exit";
        Assertions.assertEquals(Command.EXIT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showAccounts_SHOW_ACCOUNTS() {
        String input = "show accounts";
        Assertions.assertEquals(Command.SHOW_ACCOUNTS, menu.checkCommand(input));
    }


    @Test
    void checkCommand_showAccount_UNKNOWN() {
        String input = "show account";
        Assertions.assertEquals(Command.UNKNOWN, menu.checkCommand(input));
    }

    @Test
    void checkCommand_newSalesRep_NEW_SALES_REP() {
        String input = "new salesrep";
        Assertions.assertEquals(Command.NEW_SALESREP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_showSalesReps_SHOW_SALESREPS() {
        String input = "show salesreps";
        Assertions.assertEquals(Command.SHOW_SALESREPS, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportLeadBySalesRep_REPORT_LEAD_BY_SALESREP() {
        String input = "report lead by salesrep";
        Assertions.assertEquals(Command.REPORT_LEAD_BY_SALESREP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportOpportunityBySalesRep_REPORT_OPPORTUNITY_BY_SALESREP() {
        String input = "report opportunity by salesrep";
        Assertions.assertEquals(Command.REPORT_OPPORTUNITY_BY_SALESREP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedWonBySalesRep_REPORT_CLOSED_WON_BY_SALESREP() {
        String input = "report closed-won by salesrep";
        Assertions.assertEquals(Command.REPORT_CLOSED_WON_BY_SALESREP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedLostBySalesRep_REPORT_CLOSED_LOST_BY_SALESREP() {
        String input = "report closed-lost by salesrep";
        Assertions.assertEquals(Command.REPORT_CLOSED_LOST_BY_SALESREP, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportOpenBySalesRep_REPORT_OPEN_BY_SALESREP() {
        String input = "report open by salesrep";
        Assertions.assertEquals(Command.REPORT_OPEN_BY_SALESREP, menu.checkCommand(input));
    }


    @Test
    void checkCommand_reportOpportunityByCountry_REPORT_OPPORTUNITY_BY_COUNTRY() {
        String input = "report opportunity by country";
        Assertions.assertEquals(Command.REPORT_OPPORTUNITY_BY_COUNTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedWonByCountry_REPORT_CLOSED_WON_BY_COUNTRY() {
        String input = "report closed-won by country";
        Assertions.assertEquals(Command.REPORT_CLOSED_WON_BY_COUNTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedLostByCountry_REPORT_CLOSED_LOST_BY_COUNTRY() {
        String input = "report closed-lost by country";
        Assertions.assertEquals(Command.REPORT_CLOSED_LOST_BY_COUNTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportOpenByCountry_REPORT_OPEN_BY_COUNTRY() {
        String input = "report open by country";
        Assertions.assertEquals(Command.REPORT_OPEN_BY_COUNTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportOpportunityByCity_REPORT_OPPORTUNITY_BY_CITY() {
        String input = "report opportunity by city";
        Assertions.assertEquals(Command.REPORT_OPPORTUNITY_BY_CITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedWonByCity_REPORT_CLOSED_WON_BY_CITY() {
        String input = "report closed-won by city";
        Assertions.assertEquals(Command.REPORT_CLOSED_WON_BY_CITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedLostByCity_REPORT_CLOSED_LOST_BY_CITY() {
        String input = "report closed-lost by city";
        Assertions.assertEquals(Command.REPORT_CLOSED_LOST_BY_CITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportOpenByCity_REPORT_OPEN_BY_CITY() {
        String input = "report open by city";
        Assertions.assertEquals(Command.REPORT_OPEN_BY_CITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportOpportunityByIndustry_REPORT_OPPORTUNITY_BY_INDUSTRY() {
        String input = "report opportunity by industry";
        Assertions.assertEquals(Command.REPORT_OPPORTUNITY_BY_INDUSTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedWonByIndustry_REPORT_CLOSED_WON_BY_INDUSTRY() {
        String input = "report closed-won by industry";
        Assertions.assertEquals(Command.REPORT_CLOSED_WON_BY_INDUSTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportClosedLostByIndustry_REPORT_CLOSED_LOST_BY_INDUSTRY() {
        String input = "report closed-lost by industry";
        Assertions.assertEquals(Command.REPORT_CLOSED_LOST_BY_INDUSTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_reportOpenByIndustry_REPORT_OPEN_BY_INDUSTRY() {
        String input = "report open by industry";
        Assertions.assertEquals(Command.REPORT_OPEN_BY_INDUSTRY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_meanEmployeeCount_MEAN_EMPLOYEECOUNT() {
        String input = "mean employeecount";
        Assertions.assertEquals(Command.MEAN_EMPLOYEECOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_medianEmployeeCount_MEDIAN_EMPLOYEECOUNT() {
        String input = "median employeecount";
        Assertions.assertEquals(Command.MEDIAN_EMPLOYEECOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_maxEmployeeCount_MAX_EMPLOYEECOUNT() {
        String input = "max employeecount";
        Assertions.assertEquals(Command.MAX_EMPLOYEECOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_minEmployeeCount_MIN_EMPLOYEECOUNT() {
        String input = "min employeecount";
        Assertions.assertEquals(Command.MIN_EMPLOYEECOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_meanQuantity_MEAN_QUANTITY() {
        String input = "mean quantity";
        Assertions.assertEquals(Command.MEAN_QUANTITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_medianQuantity_MEDIAN_QUANTITY() {
        String input = "median quantity";
        Assertions.assertEquals(Command.MEDIAN_QUANTITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_maxQuantity_MAX_QUANTITY() {
        String input = "max quantity";
        Assertions.assertEquals(Command.MAX_QUANTITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_minQuantity_MIN_QUANTITY() {
        String input = "min quantity";
        Assertions.assertEquals(Command.MIN_QUANTITY, menu.checkCommand(input));
    }

    @Test
    void checkCommand_meanOppsPerAccount_MEAN_OPPS_PER_ACCOUNT() {
        String input = "mean opps per account";
        Assertions.assertEquals(Command.MEAN_OPPS_PER_ACCOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_medianOppsPerAccount_MEDIAN_OPPS_PER_ACCOUNT() {
        String input = "median opps per account";
        Assertions.assertEquals(Command.MEDIAN_OPPS_PER_ACCOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_maxOppsPerAccount_MAX_OPPS_PER_ACCOUNT() {
        String input = "max opps per account";
        Assertions.assertEquals(Command.MAX_OPPS_PER_ACCOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_minOppsPerAccount_MIN_OPPS_PER_ACCOUNT() {
        String input = "min opps per account";
        Assertions.assertEquals(Command.MIN_OPPS_PER_ACCOUNT, menu.checkCommand(input));
    }

    @Test
    void checkCommand_runScript_RUN() {
        String input = "run script_test.crm";
        Assertions.assertEquals(Command.RUN, menu.checkCommand(input));
    }

















}