package com.ironhack.enums;

public enum Product {
    // Available values
    HYBRID("Hybrid"),
    FLATBED("Flatbed"),
    BOX("Box");

    // Properties
    private final String name;

    // -----------------Methods------------------

    // To build an enum with a specific name.
    Product(String name) {
        this.name = name;
    }

    // To access the name of the enum.
    public String getName() {
        return this.name;
    }

    // To have the enum values in an array.
    private static final Product values[] = values();

    // To have the value according to the position.
    public static Product get(int ordinal) {
        return values[ordinal];
    }

    // To see if the input entered by the user is valid.
    public static boolean isValid(String input) {
        try {
            get(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // To read from the string that the user enters.
    public static Product get(String input) {
        return get(Integer.parseInt(input)-1);
    }

    // To display the menu options.
    public static String showOptions() {
        String options = "";
        for(Product product : values) {
            options += "[" + (product.ordinal()+1) + "]" + product/*.getName()*/ + " ";
        }
        return options.trim();
    }

    // To display the name of each type.
    public String toString() {
        return getName();
    }
}
