package com.ironhack.main.menu.command;

public enum Command {
    // List of available commands
    UNKNOWN("UNKNOWN", 0),
    NEW_LEAD(Keyword.NEW + " " + Keyword.LEAD, 0),
    SHOW_LEADS(Keyword.SHOW + " " + Keyword.LEADS, 0),
    SHOW_ACCOUNTS(Keyword.SHOW + " " + Keyword.ACCOUNTS, 0),
    LOOKUP_LEAD(Keyword.LOOKUP + " " + Keyword.LEAD, 1),
    CONVERT_LEAD(Keyword.CONVERT, 1),
    CLOSE_LOST_OPP(Keyword.CLOSE_LOST, 1),
    CLOSE_WON_OPP(Keyword.CLOSE_WON, 1),
    HELP(Keyword.HELP, 0),
    EXIT(Keyword.EXIT, 0);

    // Properties
    private final String symbol;
    private final int nArgs;

    // -----------------Methods------------------

    // Command constructor with symbol and number of arguments.
    private Command(String symbol, int nArgs) {
        this.symbol = symbol;
        this.nArgs = nArgs;
    }

    // To get the symbol.
    private String getSymbol() {
        return this.symbol;
    }

    // To know the number of words that make up the command.
    private int length() {
        return symbol.split(" ").length;
    }

    // To know the number of arguments it receives.
    private int nArgs() {
        return this.nArgs;
    }

    // To check if it receives arguments.
    private boolean hasArgs() {
        return this.nArgs > 0;
    }

    // To read the argument.
    public int getArg(String[] inputArgs) {
        return Integer.parseInt(inputArgs[length() + nArgs() - 1]);
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

                if (normalizedInput.startsWith(command.getSymbol()) &&
                    inputArgs.length == command.length() + command.nArgs()) {
                    if (command.hasArgs())
                        command.getArg(inputArgs);
                    return command;
                }
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
