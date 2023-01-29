package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PersonContact extends Contact {
    private String surname = "";
    private LocalDate birthDate;
    private String gender = "";

    PersonContact() {
        List<String> fieldsToEdit = getListOfFields();
        fieldsToEdit.addAll(Arrays.asList("surname", "birth", "gender"));
        setCreationTime(LocalDateTime.now());
        setLastEditTime(getCreationTime());
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        if (gender.isEmpty()) {
            return "[no data]";
        } else {
            return gender;
        }
    }

    public void setGender(String gender) {
        if (gender.equals("F") || gender.equals("M")) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
        }
    }

    public String getBirthDate() {
        if (birthDate == null) {
            return "[no data]";
        } else {
            return birthDate.toString();
        }
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (Exception e) {
            System.out.println("Bad birth date!");
        }
    }

    @Override
    public void printInfo() {
        System.out.printf("Name: %s\n", getName());
        System.out.printf("Surname: %s\n", getSurname());
        System.out.printf("Birth date: %s\n", getBirthDate());
        System.out.printf("Gender: %s\n", getGender());
        System.out.printf("Number: %s\n", getPhoneNumber());
        System.out.printf("Time created: %s\n", getCreationTime());
        System.out.printf("Time last edit: %s\n", getLastEditTime());
    }

    @Override
    public void changeValue(String fieldName, String newValue) {
        switch (fieldName.toLowerCase()) {
            case "name" -> setName(newValue);
            case "surname" -> setSurname(newValue);
            case "number" -> setPhoneNumber(newValue);
            case "birth" -> setBirthDate(newValue);
            case "gender" -> setGender(newValue);
            default -> System.out.println("Wrong command");
        }
    }

    @Override
    public String getValue(String fieldName) {
        return switch (fieldName.toLowerCase()) {
            case "name" -> getName();
            case "surname" -> getSurname();
            case "number" -> getPhoneNumber();
            case "birth" -> getBirthDate();
            case "gender" -> getGender();
            default -> "Wrong command";
        };
    }

    @Override
    public String toString() {
        return String.format("%s %s ", getName(), getSurname());
    }

}

