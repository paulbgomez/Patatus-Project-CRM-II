package com.ironhack.main.menu;

import com.ironhack.classes.*;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import com.ironhack.main.menu.command.Command;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class Menu {
    // Properties
    // To display the prompt in yellow.
    private static final String USER_PROMPT = MenuColors.setColorYellow("CRM:>") + " ";

    // Location of help source.
    private static final String HELP_FILEPATH = "src/main/resources/.help";


    private final Map<Integer, Lead> leadMap = new HashMap<>();
    private final Map<Integer, Account> accountMap = new HashMap<>();
    private final Map<Integer, Opportunity> opportunityMap = new HashMap<>();
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
                    try {
                        Lead newLead = newLead();
                        leadMap.put(newLead.getId(), newLead);
                        System.out.println(">> Added new Lead: " + newLead);
                    } catch (Exception e){
                        System.out.println("Cancelling Lead creation...");
                    }
                    break;

                case LOOKUP_LEAD:
                    int idToLookup = command.getArg(inputArgs);
                    try {
                        Lead leadLookup = lookupLead(idToLookup);
                        System.out.println(leadLookup);
                    } catch (RuntimeException e) {
                        printLeadNotFound(idToLookup);
                    }
                    break;

                case SHOW_LEADS:
                    showLeads();
                    break;

                case CONVERT_LEAD:
                    int idToConvert = command.getArg(inputArgs);
                    try {
                        Lead leadConvert = lookupLead(idToConvert);
                        Account account = convertLead(leadConvert);

                        accountMap.put(account.getId(), account);
                        leadMap.remove(idToConvert);

                        System.out.println(">> Added new Account: " + account);
                        System.out.println("<< Removed Lead: " + leadConvert);

                    } catch (RuntimeException e) {
                        printLeadNotFound(idToConvert);
                    }
                    break;

                case CLOSE_WON_OPP:
                    int idToCloseWon = command.getArg(inputArgs);
                    closeWonOpp(idToCloseWon);
                    break;

                case CLOSE_LOST_OPP:
                    int idToCloseLost = command.getArg(inputArgs);
                    closeLostOpp(idToCloseLost);
                    break;

                case SHOW_ACCOUNTS:
                    showAccounts();
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

    // Method in which we ask the user for the necessary data to create a lead, and the we create a new lead.
    private Lead newLead() {

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
            return new Lead(name, phoneNumber, email, companyName);
        }
        return null;
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

    // Method to display a list with all the existing leads in the database.
    private void showLeads() {
        System.out.println(mapValuesToString(leadMap));
    }

    // Method to display all the information regarding a lead, indicating his id.
    private Lead lookupLead(int id) {
        Lead lead = leadMap.get(id);
        if (lead == null)
            throw new RuntimeException();

        return lead;
    }

    // Method to convert a lead into an opportunity. When this happens, the lead disappears
    // from the lead list, and an account is created in its place. This account includes the
    // information of the lead converted into a contact, and an opportunity with the information
    // regarding the possible sale.
    private Account convertLead(Lead lead) {

        Contact decisionMaker = createContact(lead);
        Opportunity opportunity = createOpportunity(decisionMaker);

        return createAccount(decisionMaker, opportunity);
    }

    // Method to create a contact with the information that had been registered in the lead that has been converted
    private Contact createContact(Lead lead) {
        return new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
    }

    // Method to create an opportunity: when a lead is converted to an opportunity,
    // the user will be asked for the information regarding the possible sale.
    private Opportunity createOpportunity(Contact decisionMaker) {
        String productOption;
        do {
            System.out.print("Product (" + Product.showOptions() + "): ");
            productOption = scanner.nextLine().trim();
        } while (!Product.isValid(productOption));
        Product product = Product.get(productOption);

        System.out.print("Quantity: ");
        int quantity = readNonNegativeInt("VALID Quantity:");


        Opportunity opportunity = new Opportunity(decisionMaker, product, quantity);
        opportunityMap.put(opportunity.getId(), opportunity);

        return opportunity;
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

    // Method to create an account: when a lead becomes an opportunity, an account is generated that
    // includes your contact (with the information that we had saved in your lead), and the information
    // regarding the possible sale, which will be asked to the user.
    private Account createAccount(Contact contact, Opportunity opportunity) {
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

        Account account = new Account(industry, employeeCount, city, country, new ArrayList<>(), new ArrayList<>());
        account.addToContactList(contact);
        account.addToOpportunityList(opportunity);

        return account;
    }

    // With this method, we will close an opportunity as successful, indicating the id number.
    private void closeWonOpp(int id) {
        try {
            opportunityMap.get(id).closeWon();
        } catch (NullPointerException e) {
            printOpportunityNotFound(id);
        }
    }

    // With this method, we will close an opportunity as lost, indicating the id number.
    private void closeLostOpp(int id) {
        try {
            opportunityMap.get(id).closeLost();
        } catch (NullPointerException e) {
            printOpportunityNotFound(id);
        }
    }

    // Method to display the list of accounts that are registered in the database.
    private void showAccounts() {
        System.out.println(mapValuesToString(accountMap));
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

    // Method that shows an error message when the user searches for a lead with an id that does not exist.
    private void printLeadNotFound(int id) {
        System.out.println("Lead with id=" + id + " not found.");
    }

    // Method that displays an error message when the user searches for an opportunity with an id that does not exist
    private void printOpportunityNotFound(int id) {
        System.out.println("Opportunity with id=" + id + " not found.");
    }

    // Method to display the values in a more user-friendly way
    public <T, T2> String mapValuesToString(Map<T2, T> map) {
        return map.values().stream()
                .map(T::toString)
                .collect(Collectors.joining("\n"));
    }

}
