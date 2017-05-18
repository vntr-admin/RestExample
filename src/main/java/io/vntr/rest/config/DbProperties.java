package io.vntr.rest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by robertlindquist on 5/17/17.
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "db")
public class DbProperties {
    public static final String H2_DRIVER = "org.h2.Driver";
    private String url;
    private String username;
    private String password;

    public DbProperties() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
