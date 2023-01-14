package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String name;
    private String surname;
    private String phoneNumber;

    Contact(String name, String surname, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        Pattern patternPhone = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*");
        Matcher phoneMatcher = patternPhone.matcher(phoneNumber);
        this.phoneNumber = phoneMatcher.matches() ? phoneNumber : "";
        System.out.println(this.phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        String correctPhoneNumber = hasNumber() ? phoneNumber : "[no number]";
        return String.format("%s %s, %s", name, surname, correctPhoneNumber);
    }

    public boolean hasNumber() {
        return !phoneNumber.isEmpty();
    }

}

