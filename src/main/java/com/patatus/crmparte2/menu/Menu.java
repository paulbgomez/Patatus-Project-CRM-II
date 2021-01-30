package com.patatus.crmparte2.menu;

import com.patatus.crmparte2.menu.command.Command;
import com.patatus.crmparte2.controller.Controller;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;

import java.io.File;
import java.util.*;


public class Menu {
    // Properties
    // To display the prompt in yellow.
    private static final String USER_PROMPT = MenuColors.setColorYellow("CRM:>") + " ";

    // Location of help source.
    private static final String HELP_FILEPATH = "src/main/resources/.help";

    private final Controller controller = new Controller();
    private final Scanner scanner = new Scanner(System.in);

    // Show main menu and takes you to each method according to the command type.
    public void show(){

        while (true) {
            System.out.print(USER_PROMPT);
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) continue;

            Command command = checkCommand(userInput);

            String[] inputArgs = getArgsFromInput(userInput);
            switch(command) {
                case NEW_LEAD:
                    System.out.print("Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Phone number: ");
                    String phoneNumber = readFormattedString("VALID phone number: ", "\\+?\\d{9,13}");
                    System.out.print("Email address: ");
                    String email =  readFormattedString("VALID email address: ", "[\\w-.]+@(?:[\\w-]+\\.)+[\\w-]+");
                    System.out.print("Company name: ");
                    String companyName = scanner.nextLine().trim();
                    System.out.print("Are you sure all the data is ok? (Y || n): ");
                    if (scanner.nextLine().equalsIgnoreCase("Y")){
                        System.out.println(controller.newLead(name, phoneNumber, email, companyName));
                    }
                    else {
                        System.out.println("Cancelling Lead creation...");
                    }
                    break;

                case LOOKUP_LEAD:
                    int idToLookup = command.getArg(inputArgs);
                    System.out.println(controller.lookupLead(idToLookup));
                    break;

                case SHOW_LEADS:
                    System.out.println(controller.showLeads());
                    break;

                case CONVERT_LEAD:
                    int idToConvert = command.getArg(inputArgs);
                    if(!controller.checkIfExistsLead(idToConvert)) {
                        System.out.println(controller.printLeadNotFound(idToConvert));
                        break;
                    }

                    String productOption;
                    do {
                        System.out.print("Product (" + Product.showOptions() + "): ");
                        productOption = scanner.nextLine().trim();
                    } while (!Product.isValid(productOption));
                    Product product = Product.get(productOption);

                    System.out.print("Quantity: ");
                    int quantity = readNonNegativeInt("VALID Quantity: ");

                    String industryOption;
                    do {
                        System.out.print("Industry (" + Industry.showOptions() + "): ");
                        industryOption = scanner.nextLine().trim();
                    } while (!Industry.isValid(industryOption));
                    Industry industry = Industry.get(industryOption);

                    System.out.print("Number of employees: ");
                    int employeeCount = readNonNegativeInt("VALID Number of employees:");
                    System.out.print("City: ");
                    String city = scanner.nextLine().trim();
                    System.out.print("Country: ");
                    String country = scanner.nextLine().trim();

                    System.out.println(controller.convertLead(idToConvert, product, quantity, industry, employeeCount, city, country));
                    break;

                case CLOSE_WON_OPP:
                    int idToCloseWon = command.getArg(inputArgs);
                    System.out.println(controller.closeWonOpp(idToCloseWon));
                    break;

                case CLOSE_LOST_OPP:
                    int idToCloseLost = command.getArg(inputArgs);
                    System.out.println(controller.closeLostOpp(idToCloseLost));
                    break;

                case SHOW_ACCOUNTS:
                    System.out.println(controller.showAccounts());
                    break;

                case HELP:
                    printHelp();
                    break;

                case UNKNOWN:
                    printUnknownCommand(userInput);
                    break;

                case EXIT:
                    System.out.println("talué!");
                default:
                    return;
            }
        }
    }

    // This method allows us to normalize the user's input and obtain their arguments.
    private String[] getArgsFromInput(String userInput) {
        return userInput.toUpperCase().trim().split(" +");
    }

    // Method to check if a command is valid or not.
    public Command checkCommand(String userInput) {
        return Command.getCommand(userInput);
    }

    // Method to check that the string that is needed has the required format.
    private String readFormattedString(String onBadInput, String regex) {
        String input;
        int c = 0;
        while (true) {
            input = scanner.nextLine().trim();
            if (input.matches(regex))
                break;
            c++;
            if (c%3 == 0) {
                onBadInput = "Porfa, deja de vacilarme TT.TT\n" + onBadInput; // cada 3 inputs malos se frustra más
            }
            System.out.print(onBadInput);
        }
        if (c > 3)
            System.out.println("POR FIN");
        return input;
    }

    // Method to verify that the user does not enter a negative number
    private int readNonNegativeInt(String onBadInput) {
        int n = -1;
        int c = 0;
        while (n < 0) {
            String line = scanner.nextLine().trim();
            try {
                n = Integer.parseInt(line);
                if (n < 0)
                    System.out.print(onBadInput);
            } catch (Exception e) {
                System.out.print(onBadInput);
            }
            c++;
            if (c%3 == 0) {
                onBadInput = "Porfa, deja de vacilarme TT.TT\n" + onBadInput; // cada 3 inputs malos se frustra más
            }
        }
        if (c > 3)
            System.out.println("POR FIN");
        return n;
    }

    // Method to show, at any time, the commands that the user has at his disposal.
    private void printHelp() {
        try {
            File file = new File(HELP_FILEPATH);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                //System.out.println(MenuColors.setColorYellow(fileScanner.nextLine()));
                System.out.println(fileScanner.nextLine());
            }
        } catch (Exception e) {
            //System.out.println(MenuColors.setColorRed("Error: Help file could not be found."));
            System.out.println("Error: Help file could not be found.");
        }
    }

    // Method that displays an error message when the user enters a command that is not valid
    private void printUnknownCommand(String userInput) {
        //System.out.println(MenuColors.setColorRed("'" + userInput + "' is not a valid command."));
        System.out.println("'" + userInput + "' is not a valid command.");
    }
}
