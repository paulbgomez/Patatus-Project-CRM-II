package com.patatus.crmparte2.menu.command;

public class Keyword {
    // All the keywords you can use in the CRM system.
    public static final String NEW = "NEW";
    public static final String LEAD = "LEAD";
    public static final String SHOW = "SHOW";
    public static final String LEADS = "LEADS";
    public static final String ACCOUNTS = "ACCOUNTS";
    public static final String LOOKUP = "LOOKUP";
    public static final String CONVERT = "CONVERT";
    public static final String CLOSE_WON = "CLOSE-WON";
    public static final String CLOSE_LOST = "CLOSE-LOST";
    public static final String SALESREP = "SALESREP";
    public static final String SALESREPS = "SALESREPS";
    public static final String OPPORTUNITY = "OPPORTUNITY";
    public static final String REPORT = "REPORT";
    public static final String BY = "BY";
    public static final String CLOSED_WON = "CLOSED-WON";
    public static final String CLOSED_LOST = "CLOSED-LOST";
    public static final String OPEN = "OPEN";
    public static final String PRODUCT = "PRODUCT";
    public static final String COUNTRY = "COUNTRY";
    public static final String CITY = "CITY";
    public static final String INDUSTRY = "INDUSTRY";
    public static final String MEAN = "MEAN";
    public static final String MEDIAN = "MEDIAN";
    public static final String MAX = "MAX";
    public static final String MIN = "MIN";
    public static final String EMPLOYEECOUNT = "EMPLOYEECOUNT";
    public static final String QUANTITY = "QUANTITY";
    public static final String OPPS = "OPPS";
    public static final String PER = "PER";
    public static final String ACCOUNT = "ACCOUNT";
    public static final String HELP = "HELP";
    public static final String EXIT = "EXIT";
    public static final String RUN = "RUN";

    // combinations of keywords
    static final String REPORT_LEAD_BY = REPORT + " " + LEAD + " " + BY;
    static final String REPORT_OPPORTUNITY_BY = REPORT + " " + OPPORTUNITY + " " + BY;
    static final String REPORT_CLOSED_WON_BY = REPORT + " " + CLOSED_WON + " " + BY;
    static final String REPORT_CLOSED_LOST_BY = REPORT + " " + CLOSED_LOST + " " + BY;
    static final String REPORT_OPEN_BY = REPORT + " " + OPEN + " " + BY;
}
