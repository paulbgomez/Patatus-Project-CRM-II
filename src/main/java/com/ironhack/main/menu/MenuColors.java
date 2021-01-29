package com.ironhack.main.menu;

public class MenuColors {
    private static final String RESET = "\033[0m";  // Text Reset
    private static final String YELLOW = "\033[0;33m";  // YELLOW
    private static final String RED = "\033[0;31m";     // RED

    private static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    
    public static String setColorWhiteBold(String string) {
        return WHITE_BOLD + string + RESET;
    }

    public static String setColorYellow(String string) {
        return YELLOW + string + RESET;
    }

    public static String setColorRed(String string) {
        return RED + string + RESET;
    }
}
