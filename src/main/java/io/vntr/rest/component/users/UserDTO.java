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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
        if (firstName != null ? !firstName.equals(userDTO.firstName) : userDTO.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userDTO.lastName) : userDTO.lastName != null) return false;
        if (created != null ? !created.equals(userDTO.created) : userDTO.created != null) return false;
        return lastUpdated != null ? lastUpdated.equals(userDTO.lastUpdated) : userDTO.lastUpdated == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }
}
