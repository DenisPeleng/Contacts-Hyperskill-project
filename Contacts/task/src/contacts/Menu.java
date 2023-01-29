package contacts;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startMenu() {

        System.out.print("[menu] Enter action (add, list, search, count, exit):");
        String command = scanner.nextLine();
        switch (command) {
            case "add" -> menuAddContact();
            case "list" -> printContactsList();
            case "search" -> doSearch();
            case "count" -> System.out.printf("The Phone Book has %d records.\n\n", PhoneBook.contactsCount());
            case "exit" -> System.exit(0);
            default -> {
                System.out.println("Wrong command\n");
                startMenu();
            }
        }
    }

    private static void doSearch() {
        System.out.println("Enter search query:");
        String textToSearch = scanner.nextLine();
        List<Contact> foundedContacts = PhoneBook.searchContact(textToSearch);
        if (foundedContacts.size() > 0) {
            System.out.printf("Found %d results:\n", foundedContacts.size());
            for (int i = 0; i < foundedContacts.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, foundedContacts.get(i).toString());
            }
            System.out.println();
            menuSearchContact(foundedContacts);
        } else {
            System.out.println("Nothing founded!\n");
            startMenu();
        }

    }

    private static void menuSearchContact(List<Contact> foundedContacts) {
        System.out.print("[search] Enter action ([number], back, again):");
        String command = scanner.nextLine();
        switch (command) {
            case "again" -> doSearch();
            case "back" -> startMenu();
            default -> {
                Contact contactByNumber = getContactByNumber(command, foundedContacts.size());
                if (contactByNumber != null) {
                    contactByNumber.printInfo();
                    System.out.println();
                    menuRecord(contactByNumber);
                } else {
                    System.out.println("Error command");
                    menuSearchContact(foundedContacts);
                }
            }
        }
    }

    private static Contact getContactByNumber(String number, int contactListSize) {
        Contact contactByNumber = null;
        int numberOfRecord = -1;
        try {
            numberOfRecord = Integer.parseInt(number);
        } catch (Exception ex) {
            System.out.println("Wrong command");
        }
        if (numberOfRecord > 0 && numberOfRecord <= contactListSize) {
            contactByNumber = PhoneBook.getContact(numberOfRecord);
        }
        return contactByNumber;
    }

    private static void menuAddContact() {
        System.out.print("Enter the type (person, organization):");
        String typeContact = scanner.nextLine();
        Contact newContact;
        newContact = switch (typeContact) {
            case "person" -> newPersonContact();
            case "organization" -> newOrganizationContact();
            default -> null;
        };
        if (newContact == null) {
            System.out.println("Error\n");

        } else {
            PhoneBook.addContact(newContact);
            System.out.println("The record added.\n");
        }
        startMenu();
    }

    private static Contact newPersonContact() {
        PersonContact newPersonContact = new PersonContact();
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        newPersonContact.setName(name);
        System.out.println("Enter the surname:");
        String surname = scanner.nextLine();
        newPersonContact.setSurname(surname);
        System.out.println("Enter birth date:");
        String birthDate = scanner.nextLine();
        newPersonContact.setBirthDate(birthDate);
        System.out.println("Enter the gender (M, F)");
        String gender = scanner.nextLine();
        newPersonContact.setGender(gender);
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        newPersonContact.setPhoneNumber(number);
        return newPersonContact;
    }

    private static Contact newOrganizationContact() {
        OrganizationContact newOrganizationContact = new OrganizationContact();
        System.out.println("Enter the organization name:");
        String name = scanner.nextLine();
        newOrganizationContact.setName(name);
        System.out.println("Enter the address:");
        String address = scanner.nextLine();
        newOrganizationContact.setAddress(address);
        System.out.println("Enter number:");
        String number = scanner.nextLine();
        newOrganizationContact.setPhoneNumber(number);
        return newOrganizationContact;
    }

    private static void menuRemoveContact(Contact contact) {
        PhoneBook.removeContact(contact);
        System.out.println("Removed!");
        System.out.println();
    }

    private static void printContactsList() {
        if (PhoneBook.contactsCount() < 1) {
            System.out.println("No records!");
        } else {
            PhoneBook.printContactList();
            System.out.println();
            menuList();
        }
    }

    private static void menuList() {
        System.out.print("[list] Enter action ([number], back):\n");
        String command = scanner.nextLine();
        switch (command) {
            case "again" -> doSearch();
            case "back" -> startMenu();
            default -> {
                Contact contactByNumber = getContactByNumber(command, PhoneBook.contactsCount());
                if (contactByNumber != null) {
                    contactByNumber.printInfo();
                    System.out.println();
                    menuRecord(contactByNumber);
                } else {
                    System.out.println("Wrong command");
                    menuList();
                }
            }
        }
    }

    private static void menuRecord(Contact contact) {
        System.out.println("[record] Enter action (edit, delete, menu):");
        String command = scanner.nextLine();
        switch (command) {
            case "edit" -> menuEditContact(contact);
            case "delete" -> menuRemoveContact(contact);
            case "menu" -> startMenu();
            default -> menuRecord(contact);
        }
    }

    private static void menuEditContact(Contact contact) {
        System.out.printf("Select a field %s:\n", contact.getFullStrOfFields());
        String rowToEdit = scanner.nextLine();
        if (contact.getFullStrOfFields().contains(rowToEdit.toLowerCase())) {
            System.out.printf("Enter %s:\n", rowToEdit);
            String newValue = scanner.nextLine();
            PhoneBook.removeContact(contact);
            contact.changeValue(rowToEdit, newValue);
            PhoneBook.editRecord(contact);
            System.out.println("Saved");
        } else {
            System.out.println("Wrong command");
        }
        menuRecord(contact);
    }
}
