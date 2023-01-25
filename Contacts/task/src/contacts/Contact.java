package contacts;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String name;
    private String phoneNumber;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;
    private boolean isPerson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        if (phoneNumber.isEmpty()) {
            return "[no number]";
        } else {
            return phoneNumber;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        Pattern patternPhone = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*");
        Matcher phoneMatcher = patternPhone.matcher(phoneNumber);
        this.phoneNumber = phoneMatcher.matches() ? phoneNumber : "";
        System.out.println(this.phoneNumber);
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }

}
