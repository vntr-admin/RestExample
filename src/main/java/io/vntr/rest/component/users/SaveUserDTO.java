package io.vntr.rest.component.users;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class SaveUserDTO {
    private String password;
    private String username;

    public SaveUserDTO() {
    }

    public SaveUserDTO(String password, String username) {
        this.password = password;
        this.username = username;
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
}
