package io.vntr.rest.component.users;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class SaveUserDTO {
    private String firstName;
    private String lastName;

    public SaveUserDTO() {
    }

    public SaveUserDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
