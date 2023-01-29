package contacts;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> listOfFields;
    private String name;
    private String phoneNumber;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    protected Contact() {
        listOfFields = new ArrayList<>(Arrays.asList("name", "number"));
    }

    public List<String> getListOfFields() {
        return listOfFields;
    }

    public void setListOfFields(List<String> listOfFields) {
        this.listOfFields = listOfFields;
    }

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

    public abstract void printInfo();

    public abstract void changeValue(String fieldName, String newValue);

    public abstract String getValue(String fieldName);

    public String getFullStrOfFields() {
        return listOfFields.toString().replace("[", "(").replace("]", ")");
    }
    public String toFullString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : getListOfFields()
        ) {
            stringBuilder.append(getValue(value)).append(" ");
        }
        return stringBuilder.toString();
    }
}
