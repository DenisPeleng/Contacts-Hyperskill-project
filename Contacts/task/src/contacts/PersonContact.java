package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonContact extends Contact {
    private String surname = "";
    private LocalDate birthDate;
    private String gender = "";

    PersonContact() {
        setCreationTime(LocalDateTime.now());
        setLastEditTime(getCreationTime());
        setPerson(true);
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

    public void printPersonInfo() {
        System.out.printf("Name: %s\n", getName());
        System.out.printf("Surname: %s\n", getSurname());
        System.out.printf("Birth date: %s\n", getBirthDate());
        System.out.printf("Gender: %s\n", getGender());
        System.out.printf("Number: %s\n", getPhoneNumber());
        System.out.printf("Time created: %s\n", getCreationTime());
        System.out.printf("Time last edit: %s\n", getLastEditTime());
    }
    @Override
    public String toString() {

        return String.format("%s %s ", getName(), getSurname());
    }
}

