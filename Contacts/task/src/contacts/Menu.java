package contacts;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        boolean isMenuWorking = true;
        while (isMenuWorking) {
            System.out.print("Enter action (add, remove, edit, count, info, exit):");
            String command = scanner.nextLine();
            switch (command) {
                case "add" -> menuAddContact();
                case "remove" -> menuRemoveContact();
                case "edit" -> menuEditContact();
                case "count" -> System.out.printf("The Phone Book has %d records.\n\n", PhoneBook.contactsCount());
                case "info" -> menuInfoContacts();
                case "exit" -> isMenuWorking = false;
                default -> System.out.println("Wrong command");
            }
        }
    }

    private static void menuInfoContacts() {
        if (PhoneBook.contactsCount() < 1) {
            System.out.println("No records to remove!");
        } else {
            PhoneBook.printContactList();
            System.out.println("Select a record:");
            int numberOfRecord = Integer.parseInt(scanner.nextLine());
            Contact contact = PhoneBook.getContact(numberOfRecord);
            if (contact.isPerson()) {
                PersonContact contactForInfo = (PersonContact) contact;
                contactForInfo.printPersonInfo();
            } else {
                OrganizationContact contactForInfo = (OrganizationContact) contact;
                contactForInfo.printOrganizationInfo();
            }
        }
        System.out.println();
    }

    private static void menuAddContact() {
        System.out.println("Enter the type (person, organization):");
        String typeContact = scanner.nextLine();
        Contact newContact;
        newContact = switch (typeContact) {
            case "person" -> newPersonContact();
            case "organization" -> newOrganizationContact();
            default -> null;
        };
        PhoneBook.addContact(newContact);
        System.out.println("The record added.");
        System.out.println();

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
        System.out.println();
    }

    private static void menuEditContact() {
        if (PhoneBook.contactsCount() < 1) {
            System.out.println("No records to edit!");
        } else {
            PhoneBook.printContactList();
            System.out.println("Select a record:");
            int numberOfRecord = Integer.parseInt(scanner.nextLine());
            Contact oldContact = PhoneBook.getContact(numberOfRecord);
            Contact newContact;
            if (oldContact.isPerson()) {
                newContact = editPersonContact((PersonContact) oldContact);
            } else {
                newContact = editOrganizationContact((OrganizationContact) oldContact);
            }

            PhoneBook.editRecord(numberOfRecord, newContact);
            System.out.println("The record updated!");
        }
        System.out.println();
    }

    private static Contact editPersonContact(PersonContact oldContact) {
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String rowToEdit = scanner.nextLine();

        switch (rowToEdit) {
            case "name" -> {
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                oldContact.setName(name);
            }
            case "surname" -> {
                System.out.println("Enter surname");
                String surname = scanner.nextLine();
                oldContact.setSurname(surname);
            }
            case "number" -> {
                System.out.println("Enter number:");
                String number = scanner.nextLine();
                oldContact.setPhoneNumber(number);
            }
            case "birth" -> {
                System.out.println("Enter birth:");
                String birthDate = scanner.nextLine();
                oldContact.setBirthDate(birthDate);
            }
            case "gender" -> {
                System.out.println("Enter gender:");
                String gender = scanner.nextLine();
                oldContact.setGender(gender);
            }
            default -> System.out.println("Wrong command");
        }
        return oldContact;
    }

    private static Contact editOrganizationContact(OrganizationContact oldContact) {
        System.out.println("Select a field (address, number):");
        String rowToEdit = scanner.nextLine();
        switch (rowToEdit) {
            case "address" -> {
                System.out.println("Enter address:");
                String address = scanner.nextLine();
                oldContact.setAddress(address);
            }
            case "number" -> {
                System.out.println("Enter number:");
                String number = scanner.nextLine();
                oldContact.setPhoneNumber(number);
            }
            default -> System.out.println("Wrong command");
        }
        return oldContact;
    }


}
