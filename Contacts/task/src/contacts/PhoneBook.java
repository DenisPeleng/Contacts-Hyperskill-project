package contacts;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    private static List<Contact> contactList = new ArrayList<>();
    private static String filePath = "";

    public static void setFilePath(String filePath) {
        PhoneBook.filePath = filePath;
    }

    public static void addContact(Contact contact) {
        contactList.add(contact);
        saveContactsList();
    }

    public static int contactsCount() {
        return contactList.size();
    }

    public static void printContactList() {
        for (int i = 0; i < contactList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, contactList.get(i).toString());
        }
    }

    public static void removeContact(Contact contact) {
        contactList.remove(contact);
        saveContactsList();
    }

    public static void editRecord(Contact newContact) {
        newContact.setLastEditTime(LocalDateTime.now());
        contactList.add(newContact);
        saveContactsList();
    }

    public static Contact getContact(int numberRecord) {
        return contactList.get(numberRecord - 1);
    }

    public static List<Contact> searchContact(String searchText) {
        List<Contact> foundContacts = new ArrayList<>();
        for (Contact contact : contactList
        ) {
            Pattern pattern = Pattern.compile(searchText, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(contact.toFullString());
            if (matcher.find()) {
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }

    private static void saveContactsList() {
        if (!filePath.isEmpty())
            try {
                serializePhoneBook(contactList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    private static void serializePhoneBook(Object obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    private static List<Contact> deserializePhoneBook() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        List<Contact> obj = (List<Contact>) ois.readObject();
        ois.close();
        return obj;
    }

    public static void readPhoneBookFromFile() {
        try {
            PhoneBook.contactList = deserializePhoneBook();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
