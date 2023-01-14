package contacts;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        boolean isMenuWorking = true;
        while (isMenuWorking) {
            System.out.print("Enter action (add, remove, edit, count, list, exit):");
            String command = scanner.nextLine();
            switch (command) {
                case "add" -> menuAddContact();
                case "remove" -> menuRemoveContact();
                case "edit" -> menuEditContact();
                case "count" -> System.out.printf("The Phone Book has %d records.\n", PhoneBook.contactsCount());
                case "list" -> PhoneBook.printContactList();
                case "exit" -> isMenuWorking = false;
                default -> System.out.println("Wrong command");
            }
        }
    }

    private static void menuAddContact() {
        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        Contact contact = new Contact(name, surname, number);
        PhoneBook.addContact(contact);
        System.out.println("The record added.");
    }

    private static void menuRemoveContact() {
        if (PhoneBook.contactsCount() < 1) {
            System.out.println("No records to remove!");
        } else {
            PhoneBook.printContactList();
            System.out.println("Select a record:");
            int numberOfRecord = Integer.parseInt(scanner.nextLine());
            PhoneBook.removeRecord(numberOfRecord);
            System.out.println("The record removed!");
        }
    }

    private static void menuEditContact() {
        if (PhoneBook.contactsCount() < 1) {
            System.out.println("No records to edit!");
        } else {
            PhoneBook.printContactList();
            System.out.println("Select a record:");
            int numberOfRecord = Integer.parseInt(scanner.nextLine());
            Contact oldContact = PhoneBook.getContact(numberOfRecord);
            System.out.println("Select a field (name, surname, number):");
            String rowToEdit = scanner.nextLine();
            Contact newContact = oldContact;
            switch (rowToEdit) {
                case "name" -> {
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    newContact = new Contact(name, oldContact.getSurname(), oldContact.getPhoneNumber());
                }
                case "surname" -> {
                    System.out.println("Enter surname");
                    String surname = scanner.nextLine();
                    newContact = new Contact(oldContact.getName(), surname, oldContact.getPhoneNumber());
                    System.out.println();
                }
                case "number" -> {
                    System.out.println("Enter number:");
                    String number = scanner.nextLine();
                    newContact = new Contact(oldContact.getName(), oldContact.getSurname(), number);
                }
                default -> System.out.println("Wrong command");
            }
            PhoneBook.editRecord(numberOfRecord, newContact);
            System.out.println("The record updated!");
        }
    }

}
