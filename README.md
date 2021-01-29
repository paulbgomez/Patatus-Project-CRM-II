# Patatus-Project2-CRM

# CRM SYSTEM

## INSTALLATION

1. Clone the repo in your own computer, or download a zip with the project.

## FEATURES

1. Console styled interface.
2. Ability to show a command list typing <code>help</code> **(additional feature)**.
3. Ability to show a list of all Accounts **(additional feature)**.

## USAGE

1. Run <code>Main.java</code> file in your favourite IDE.
2. Type your commands in the console (case insensitive).
3. First you need to create leads with <code>New Lead</code> command.
4. You can lookup individual leads by id, or show a list of them.
5. You can convert leads into opportunities with <code>convert</code> command.
6. Once converted, a new account is created containing the contact and opportunity.
7. You can close-won or close-lost opportunities.
8. If you wonder how to exit, simply type exit. Fancy stuff.

## LIST OF AVAILABLE COMMANDS
|Command|Action|
|-------|------|
|new lead|Creates and registers a new Lead in the system.|
|show leads|Shows a list of the current Leads registered.|
|lookup lead <id>|Looks for a Lead with corresponding <id> and shows its info.|
|convert <id>|Converts a Lead with corresponding <id> into a new Opportunity,prompting the user for the values needed during the conversion.|
|show accounts|Shows a list of the current Accounts registered.|
|close-won <id>|Closes an Opportunity with the corresponding <id>, with a sale made.|
|close-lost <id>|Closes an Opportunity with the corresponding <id>, with no sale made.|
|help|Shows this help screen.|
|exit|Exits the CRM console.|

## UML DIAGRAMS
![diagram1](uml/crm_classesDiagram_final.png)
![diagram2](uml/crm_useCases_final.png)

## SCREENSHOTS
![screenshot1](screenshots/screenshot1.jpg)
![screenshot1](screenshots/screenshot2.jpg)
![screenshot1](screenshots/screenshot3.jpg)
![screenshot1](screenshots/screenshot4.jpg)
![screenshot1](screenshots/screenshot5.jpg)

### Patatús team
Alejandro Martínez, Ángel Serrano, Paúl Barroso, Paula Sardinero, Rubén Navarro. 
