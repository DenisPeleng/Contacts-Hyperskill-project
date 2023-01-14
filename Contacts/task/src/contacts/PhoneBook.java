package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private static final List<Contact> contactList = new ArrayList<>();

    public static void addContact(Contact contact) {
        contactList.add(contact);
    }

    public static int contactsCount() {
        return contactList.size();
    }

    public static void printContactList() {
        for (int i = 0; i < contactList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, contactList.get(i).toString());
        }
    }

    public static void removeRecord(int numberRecord) {
        contactList.remove(numberRecord - 1);
    }

    public static void editRecord(int numberRecord, Contact newContact) {
        contactList.set(numberRecord - 1, newContact);
    }

    public static Contact getContact(int numberRecord) {
        return contactList.get(numberRecord - 1);
    }

}
