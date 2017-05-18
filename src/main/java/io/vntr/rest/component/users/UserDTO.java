package io.vntr.rest.component.users;

import io.vntr.rest.component.DateTimeDTO;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private DateTimeDTO created;
    private DateTimeDTO lastUpdated;

    public UserDTO() {
    }

    public UserDTO(Integer id, String firstName, String lastName, DateTimeDTO created, DateTimeDTO lastUpdated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public DateTimeDTO getCreated() {
        return created;
    }

    public void setCreated(DateTimeDTO created) {
        this.created = created;
    }

    public DateTimeDTO getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(DateTimeDTO lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
