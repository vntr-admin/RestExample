package io.vntr.rest.login;

import io.vntr.rest.component.users.UserService;
import io.vntr.rest.domain.User;
import io.vntr.rest.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by robertlindquist on 6/20/17.
 */
@RestController
public class LoginController {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private TokenService tokenService;

    private UserService userService;

    @Autowired
    public LoginController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> requestToken(HttpServletRequest request) {
        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Basic")) {
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), UTF_8);
            final String[] values = credentials.split(":", 2);
            String username = values[0];
            String password = values[1];
            User user = userService.findUsersByUsername(username).get(0);
            if(user.getPassword().equals(password)) {
                String token = tokenService.generateNewToken(username, password);
                return ResponseEntity.ok(token);
            }
            else {
                return new ResponseEntity<>((String)null, HttpStatus.UNAUTHORIZED);
            }
        }

        //TODO: do this
        return new ResponseEntity<>((String)null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
