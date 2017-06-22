package io.vntr.rest.component.users;

import io.vntr.rest.component.DateTimeDTO;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private DateTimeDTO created;
    private DateTimeDTO lastUpdated;

    public UserDTO() {
    }

    public UserDTO(Integer id, String password, String username, DateTimeDTO created, DateTimeDTO lastUpdated) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (password != null ? !password.equals(userDTO.password) : userDTO.password != null) return false;
        if (username != null ? !username.equals(userDTO.username) : userDTO.username != null) return false;
        if (created != null ? !created.equals(userDTO.created) : userDTO.created != null) return false;
        return lastUpdated != null ? lastUpdated.equals(userDTO.lastUpdated) : userDTO.lastUpdated == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }
}
