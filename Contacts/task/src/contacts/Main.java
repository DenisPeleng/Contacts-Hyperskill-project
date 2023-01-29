package contacts;

public class Main {
    public static void main(String[] args) {
        if (args.length!=0){
            PhoneBook.setFilePath(args[0]);
            PhoneBook.readPhoneBookFromFile();
        }
        Menu.startMenu();
    }
}
