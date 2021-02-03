package com.patatus.crmparte2.menu.command;


public enum Command {
    // List of available commands
    NEW_LEAD(Keyword.NEW + " " + Keyword.LEAD),
    SHOW_LEADS(Keyword.SHOW + " " + Keyword.LEADS),
    SHOW_ACCOUNTS(Keyword.SHOW + " " + Keyword.ACCOUNTS),
    LOOKUP_LEAD(Keyword.LOOKUP + " " + Keyword.LEAD, 1),
    CONVERT_LEAD(Keyword.CONVERT, 1),
    CLOSE_LOST_OPP(Keyword.CLOSE_LOST, 1),
    CLOSE_WON_OPP(Keyword.CLOSE_WON, 1),

    NEW_SALESREP(Keyword.NEW + " " + Keyword.SALESREP),
    SHOW_SALESREPS(Keyword.SHOW + " " + Keyword.SALESREPS),

    REPORT_LEAD_BY_SALESREP(Keyword.REPORT_LEAD_BY + " " + Keyword.SALESREP),
    REPORT_OPPORTUNITY_BY_SALESREP(Keyword.REPORT_OPPORTUNITY_BY + " " + Keyword.SALESREP),
    REPORT_CLOSED_WON_BY_SALESREP(Keyword.REPORT_CLOSED_WON_BY + " " + Keyword.SALESREP),
    REPORT_CLOSED_LOST_BY_SALESREP(Keyword.REPORT_CLOSED_LOST_BY + " " + Keyword.SALESREP),
    REPORT_OPEN_BY_SALESREP(Keyword.REPORT_OPEN_BY + " " + Keyword.SALESREP),

    REPORT_LEAD_BY_PRODUCT(Keyword.REPORT_LEAD_BY + " " + Keyword.PRODUCT),
    REPORT_OPPORTUNITY_BY_PRODUCT(Keyword.REPORT_OPPORTUNITY_BY + " " + Keyword.PRODUCT),
    REPORT_CLOSED_WON_BY_PRODUCT(Keyword.REPORT_CLOSED_WON_BY + " " + Keyword.PRODUCT),
    REPORT_CLOSED_LOST_BY_PRODUCT(Keyword.REPORT_CLOSED_LOST_BY + " " + Keyword.PRODUCT),
    REPORT_OPEN_BY_PRODUCT(Keyword.REPORT_OPEN_BY + " " + Keyword.PRODUCT),

    REPORT_LEAD_BY_COUNTRY(Keyword.REPORT_LEAD_BY + " " + Keyword.COUNTRY),
    REPORT_OPPORTUNITY_BY_COUNTRY(Keyword.REPORT_OPPORTUNITY_BY + " " + Keyword.COUNTRY),
    REPORT_CLOSED_WON_BY_COUNTRY(Keyword.REPORT_CLOSED_WON_BY + " " + Keyword.COUNTRY),
    REPORT_CLOSED_LOST_BY_COUNTRY(Keyword.REPORT_CLOSED_LOST_BY + " " + Keyword.COUNTRY),
    REPORT_OPEN_BY_COUNTRY(Keyword.REPORT_OPEN_BY + " " + Keyword.COUNTRY),

    REPORT_LEAD_BY_CITY(Keyword.REPORT_LEAD_BY + " " + Keyword.CITY),
    REPORT_OPPORTUNITY_BY_CITY(Keyword.REPORT_OPPORTUNITY_BY + " " + Keyword.CITY),
    REPORT_CLOSED_WON_BY_CITY(Keyword.REPORT_CLOSED_WON_BY + " " + Keyword.CITY),
    REPORT_CLOSED_LOST_BY_CITY(Keyword.REPORT_CLOSED_LOST_BY + " " + Keyword.CITY),
    REPORT_OPEN_BY_CITY(Keyword.REPORT_OPEN_BY + " " + Keyword.CITY),

    REPORT_LEAD_BY_INDUSTRY(Keyword.REPORT_LEAD_BY + " " + Keyword.INDUSTRY),
    REPORT_OPPORTUNITY_BY_INDUSTRY(Keyword.REPORT_OPPORTUNITY_BY + " " + Keyword.INDUSTRY),
    REPORT_CLOSED_WON_BY_INDUSTRY(Keyword.REPORT_CLOSED_WON_BY + " " + Keyword.INDUSTRY),
    REPORT_CLOSED_LOST_BY_INDUSTRY(Keyword.REPORT_CLOSED_LOST_BY + " " + Keyword.INDUSTRY),
    REPORT_OPEN_BY_INDUSTRY(Keyword.REPORT_OPEN_BY + " " + Keyword.INDUSTRY),

    MEAN_EMPLOYEECOUNT(Keyword.MEAN + " " + Keyword.EMPLOYEECOUNT),
    MEDIAN_EMPLOYEECOUNT(Keyword.MEDIAN + " " + Keyword.EMPLOYEECOUNT),
    MAX_EMPLOYEECOUNT(Keyword.MAX + " " + Keyword.EMPLOYEECOUNT),
    MIN_EMPLOYEECOUNT(Keyword.MIN + " " + Keyword.EMPLOYEECOUNT),

    MEAN_QUANTITY(Keyword.MEAN + " " + Keyword.QUANTITY),
    MEDIAN_QUANTITY(Keyword.MEDIAN + " " + Keyword.QUANTITY),
    MAX_QUANTITY(Keyword.MAX + " " + Keyword.QUANTITY),
    MIN_QUANTITY(Keyword.MIN + " " + Keyword.QUANTITY),

    MEAN_OPPS_PER_ACCOUNT(Keyword.MEAN + " " + Keyword.OPPS + " " + Keyword.PER + " " + Keyword.ACCOUNT),
    MEDIAN_OPPS_PER_ACCOUNT(Keyword.MEDIAN + " " + Keyword.OPPS + " " + Keyword.PER + " " + Keyword.ACCOUNT),
    MAX_OPPS_PER_ACCOUNT(Keyword.MAX + " " + Keyword.OPPS + " " + Keyword.PER + " " + Keyword.ACCOUNT),
    MIN_OPPS_PER_ACCOUNT(Keyword.MIN + " " + Keyword.OPPS + " " + Keyword.PER + " " + Keyword.ACCOUNT),

    HELP(Keyword.HELP),
    EXIT(Keyword.EXIT),

    RUN(Keyword.RUN, 1),

    UNKNOWN("UNKNOWN");


    // Properties
    private final String symbol;
    private final int nArgs;
    private final int keywordCount;

    // -----------------Methods------------------

    // Command constructor with symbol.
    private Command(String symbol) {
        this(symbol, 0);
    }

    // Command constructor with symbol and number of arguments.
    private Command(String symbol, int nArgs) {
        this.symbol = symbol;
        this.nArgs = nArgs;
        this.keywordCount = this.symbol.split(" ").length;
    }

    // To get the symbol.
    private String getSymbol() {
        return this.symbol;
    }

    // To know the number of words that make up the command.
    private int keywordCount() {
        return this.keywordCount;
    }

    // To know the number of arguments it receives.
    private int nArgs() {
        return this.nArgs;
    }

    // To know the total length (keywords + nargs) of the command
    private int length() { return keywordCount() + nArgs(); }

    // To check if it receives arguments.
    private boolean hasArgs() {
        return this.nArgs > 0;
    }

    // To read the int argument.
    public int getArg(String[] inputArgs) {
        return Integer.parseInt(inputArgs[length() - 1]);
    }

    public String getStringArg(String[] inputArgs) {
        return inputArgs[length() - 1];
    }

    // Method to get a command from the users input.
    public static Command getCommand(String userInput) {
        String[] inputArgs = userInput.toUpperCase().trim().split(" +");
        String normalizedInput = "";
        for (String token : inputArgs)
            normalizedInput += token + " ";
        normalizedInput = normalizedInput.trim();

        try {
            // look for a command that matches the user input
            for(Command command : values()) {

                // skip the generic unknown command
                if (command.equals(UNKNOWN)) continue;

                // skip if command length != inputArgs length
                if (command.length() != inputArgs.length) continue;

                // loop to check if input matches command
                boolean isMatch = true;
                String[] commandKeywords = command.getSymbol().split(" ");
                for(int i=0; i<commandKeywords.length; i++) {
                    isMatch = isMatch && commandKeywords[i].equals(inputArgs[i]);
                    if (!isMatch) break; // break the loop
                }
                if (!isMatch) continue; // try next command

                // try to get args if required
                if (command.hasArgs())
                    if (!command.equals(Command.RUN))
                        // get int arg
                        command.getArg(inputArgs);
                    else
                        // get string arg
                        command.getStringArg(inputArgs);
                return command;
            }
        }
        catch (Exception e) {
            return UNKNOWN;
        }
        return UNKNOWN;
    }

    // To save the values in an array
    private static final Command values[] = values();

    // To have the value according to the position.
    public static Command get(int ordinal) { return values[ordinal]; }
}
