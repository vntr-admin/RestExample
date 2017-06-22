package io.vntr.rest.filter;

import io.vntr.rest.component.users.UserService;
import io.vntr.rest.domain.Requestor;
import io.vntr.rest.domain.User;
import io.vntr.rest.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by robertlindquist on 6/20/17.
 */
@Component
public class RequestorProcessingFilter extends OncePerRequestFilter {

    @Autowired
    private Requestor requestor;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if(token != null) {
            try {
                Integer userId = tokenService.authenticate(token);
                setRequestor(userId);
            } catch(Exception e) {
                
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setRequestor(Integer userId) {
        User user = userService.getUserById(userId);
        if(user != null) {
            requestor.setId(user.getUserId());
            requestor.setUsername(user.getUsername());
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String tokenHeader = request.getHeader(AUTHORIZATION);
        if (tokenHeader != null && tokenHeader.contains(BEARER) && tokenHeader.contains(" ")) {
            String[] authParts = tokenHeader.split(" ");

            if (authParts.length == 2) {
                if (authParts[0].equals(BEARER)) {
                    return authParts[1];
                }
            }
        }
        return null;
    }

}
