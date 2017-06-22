package io.vntr.rest.token;

import io.vntr.rest.component.users.UserService;
import io.vntr.rest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by robertlindquist on 6/20/17.
 */
@Service
public class TokenService {

    private TokenEncoder tokenEncoder;
    private UserService userService;
    private Long tokenLife = 30 * 60 * 1000L;
    private Integer maxUsernameLength = 10;
    private Integer maxPasswordLength = 10;

    @Autowired
    public TokenService(TokenEncoder tokenEncoder, UserService userService) {
        this.tokenEncoder = tokenEncoder;
        this.userService = userService;
    }

    public Integer authenticate(String token) {
        String[] tokenElements = tokenEncoder.decode(token);
        String username = tokenElements[0];
        String password = tokenElements[1];
        String expirationStr = tokenElements[2];
        try {
            Date expiration = new Date(Long.parseLong(expirationStr));
            if(expiration.before(new Date())) {
                User user = userService.findUsersByUsername(username).get(0);
                if(user.getPassword().equals(password)) {
                    return user.getUserId();
                }
            }
        }
        catch(Exception e) {
        }

        return null;
    }

    public String generateNewToken(String username, String password) {
        Date expirationDate = new Date(new Date().getTime() + tokenLife);
        return tokenEncoder.encode(username, password, expirationDate.getTime());
    }

}
