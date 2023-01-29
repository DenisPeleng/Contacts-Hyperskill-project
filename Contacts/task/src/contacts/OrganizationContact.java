package contacts;

import java.time.LocalDateTime;
import java.util.List;

public class OrganizationContact extends Contact {
    private String address = "";

    OrganizationContact() {
        List<String> fieldsToEdit = getListOfFields();
        fieldsToEdit.add("address");
        setListOfFields(fieldsToEdit);
        setCreationTime(LocalDateTime.now());
        setLastEditTime(getCreationTime());
    }

    public String getAddress() {
        if (address.isEmpty()) {
            return "[no data]";
        } else {
            return address;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {

        return String.format("%s", getName());
    }

    @Override
    public void printInfo() {
        System.out.printf("Organization name: %s\n", getName());
        System.out.printf("Address: %s\n", getAddress());
        System.out.printf("Number: %s\n", getPhoneNumber());
        System.out.printf("Time created: %s\n", getCreationTime());
        System.out.printf("Time last edit: %s\n", getLastEditTime());
    }

    @Override
    public void changeValue(String fieldName, String newValue) {
        switch (fieldName.toLowerCase()) {
            case "address" -> setAddress(newValue);
            case "number" -> setPhoneNumber(newValue);
            default -> System.out.println("Wrong command");
        }
    }

    @Override
    public String getValue(String fieldName) {
        return switch (fieldName.toLowerCase()) {
            case "address" -> getAddress();
            case "number" -> getPhoneNumber();
            case "name" -> getName();
            default -> "Wrong command";
        };
    }
}
