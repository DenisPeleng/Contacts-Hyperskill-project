package contacts;

import java.time.LocalDateTime;

public class OrganizationContact extends Contact {
    private String address = "";

    OrganizationContact() {
        setCreationTime(LocalDateTime.now());
        setLastEditTime(getCreationTime());
        setPerson(false);
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

    public void printOrganizationInfo() {
        System.out.printf("Organization name: %s\n", getName());
        System.out.printf("Address: %s\n", getAddress());
        System.out.printf("Number: %s\n", getPhoneNumber());
        System.out.printf("Time created: %s\n", getCreationTime());
        System.out.printf("Time last edit: %s\n", getLastEditTime());
    }
}
