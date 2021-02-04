package com.patatus.crmparte2.menu;

import com.patatus.crmparte2.menu.command.Command;
import com.patatus.crmparte2.controller.Controller;
import com.patatus.crmparte2.model.classes.SalesRep;
import com.patatus.crmparte2.model.enums.Industry;
import com.patatus.crmparte2.model.enums.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class Menu {
    // Properties
    // To display the prompt in yellow.
    private static final String USER_PROMPT = MenuColors.setColorYellow("CRM:>") + " ";

    // Location of help source.
    private static final String HELP_FILEPATH = "src/main/resources/.help";
    // Location of script files directory.
    private static final String SCRIPTS_DIR = "src/main/resources/scripts/";

    @Autowired
    private Controller controller;

    // Show main menu and takes you to each method according to the command type.
    public void show(Scanner scanner){

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
                    String phoneNumber = readFormattedString(scanner,"VALID phone number: ", "\\+?\\d{9,13}");
                    System.out.print("Email address: ");
                    String email =  readFormattedString(scanner,"VALID email address: ", "[\\w-.]+@(?:[\\w-]+\\.)+[\\w-]+");
                    System.out.print("Company name: ");
                    String companyName = scanner.nextLine().trim();
                    SalesRep repLead = readAndFindSalesRep(scanner);
                    System.out.print("Are you sure all the data is ok? (Y || n): ");
                    if (scanner.nextLine().equalsIgnoreCase("Y")){
                        System.out.println(controller.newLead(name, phoneNumber, email, companyName, repLead));
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
                    int quantity = readNonNegativeInt(scanner,"VALID Quantity: ");

                    String industryOption;
                    do {
                        System.out.print("Industry (" + Industry.showOptions() + "): ");
                        industryOption = scanner.nextLine().trim();
                    } while (!Industry.isValid(industryOption));
                    Industry industry = Industry.get(industryOption);

                    System.out.print("Number of employees: ");
                    int employeeCount = readNonNegativeInt(scanner, "VALID Number of employees: ");
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

                case NEW_SALESREP:
                    System.out.print("Name: ");
                    String salesRepName = scanner.nextLine().trim();
                    System.out.println(controller.newSalesRep(salesRepName));
                    break;
                case SHOW_SALESREPS:
                    System.out.println("not implemented yet");
                    break;

                case REPORT_LEAD_BY_SALESREP:
                case REPORT_OPPORTUNITY_BY_SALESREP:
                case REPORT_CLOSED_WON_BY_SALESREP:
                case REPORT_CLOSED_LOST_BY_SALESREP:
                case REPORT_OPEN_BY_SALESREP:
                    System.out.println("not implemented yet");
                    break;
                    
                case REPORT_LEAD_BY_PRODUCT:
                case REPORT_OPPORTUNITY_BY_PRODUCT:
                case REPORT_CLOSED_WON_BY_PRODUCT:
                case REPORT_CLOSED_LOST_BY_PRODUCT:
                case REPORT_OPEN_BY_PRODUCT:
                    System.out.println("not implemented yet");
                    break;

                case REPORT_LEAD_BY_COUNTRY:
                case REPORT_OPPORTUNITY_BY_COUNTRY:
                case REPORT_CLOSED_WON_BY_COUNTRY:
                case REPORT_CLOSED_LOST_BY_COUNTRY:
                case REPORT_OPEN_BY_COUNTRY:
                    System.out.println("not implemented yet");
                    break;

                case REPORT_LEAD_BY_CITY:
                case REPORT_OPPORTUNITY_BY_CITY:
                case REPORT_CLOSED_WON_BY_CITY:
                case REPORT_CLOSED_LOST_BY_CITY:
                case REPORT_OPEN_BY_CITY:
                    System.out.println("not implemented yet");
                    break;

                case REPORT_LEAD_BY_INDUSTRY:
                case REPORT_OPPORTUNITY_BY_INDUSTRY:
                case REPORT_CLOSED_WON_BY_INDUSTRY:
                case REPORT_CLOSED_LOST_BY_INDUSTRY:
                case REPORT_OPEN_BY_INDUSTRY:
                    System.out.println("not implemented yet");
                    break;
                    
                case MEAN_EMPLOYEECOUNT:
                case MEDIAN_EMPLOYEECOUNT:
                case MAX_EMPLOYEECOUNT:
                case MIN_EMPLOYEECOUNT:
                    System.out.println("not implemented yet");
                    break;

                case MEAN_QUANTITY:
                case MEDIAN_QUANTITY:
                case MAX_QUANTITY:
                case MIN_QUANTITY:
                    System.out.println("not implemented yet");
                    break;

                case MEAN_OPPS_PER_ACCOUNT:
                case MEDIAN_OPPS_PER_ACCOUNT:
                case MAX_OPPS_PER_ACCOUNT:
                case MIN_OPPS_PER_ACCOUNT:
                    System.out.println("not implemented yet");
                    break;
                    
                case HELP:
                    printHelp();
                    break;

                case UNKNOWN:
                    printUnknownCommand(userInput);
                    break;

                case RUN:
                    String scriptFilename = command.getStringArg(inputArgs);
                    try {
                        show(new Scanner(new File(SCRIPTS_DIR + scriptFilename)));
                    } catch (Exception e) {
                        printFileNotFound(scriptFilename);
                    }
                    break;
                case EXIT:
                    System.out.println("talué!");
                default:
                    return;
            }
        }
    }

    private SalesRep readAndFindSalesRep(Scanner scanner) {
        int n;
        Optional<SalesRep> salesRep;
        while (true) {
            System.out.print("SalesRep id: ");
            String line = scanner.nextLine().trim();
            try {
                n = Integer.parseInt(line);
                salesRep = controller.findSalesRep(n);
                if (salesRep.isPresent()) {
                    return salesRep.get();
                }
            } catch (NumberFormatException ignore) {
            }

        }

    }

    // This method allows us to normalize the user's input and obtain their arguments.
    private String[] getArgsFromInput(String userInput) {
        String[] values = userInput.toUpperCase().trim().split(" +");
        // if the command is RUN, the script filename must remain (not applying toUppercase())
        if (values[0].equals(Command.RUN.name()))
            values[values.length-1] = userInput.trim().split(" +")[values.length-1];
        return values;
    }

    // Method to check if a command is valid or not.
    public Command checkCommand(String userInput) {
        return Command.getCommand(userInput);
    }

    // Method to check that the string that is needed has the required format.
    private String readFormattedString(Scanner scanner, String onBadInput, String regex) {
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
    private int readNonNegativeInt(Scanner scanner, String onBadInput) {
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

    // Displays an error message when a script file is not found.
    private void printFileNotFound(String filename) {
        System.out.println("'" + filename + "' file not found.");
    }
}
