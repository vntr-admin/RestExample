package io.vntr.rest.component.users;

import io.vntr.rest.component.DateTimeDTO;
import io.vntr.rest.component.DateTimeMapper;
import io.vntr.rest.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private Date date;

    @Before
    public void init() {
        date = new Date();
        userController = new UserController(new UserMapper(new DateTimeMapper()));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById() throws Exception {
        mockUserService();
        DateTimeDTO dateTimeDTO = new DateTimeMapper().fromEntity(date);
        UserDTO expectedResult = new UserDTO(468, "Daryl", "Hannah", dateTimeDTO, dateTimeDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(468);
        UserDTO result = userController.getUserById(468).getBody();
        assertTrue(response.getStatusCodeValue() == 200);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSaveUser() throws Exception {
        mockUserService();
        //TODO: do this
    }

    @Test
    public void testUpdateUser() throws Exception {
        mockUserService();
        //TODO: do this
    }

    @Test
    public void testGetUsersByLastName() throws Exception {
        mockUserService();
        //TODO: do this
    }

    private void mockUserService() throws Exception {
        when(userService.getUserById(anyInt())).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                Integer id = (Integer) invocation.getArguments()[0];
                User user = new User("Daryl", "Hannah", id);
                user.setCreatedDate(date);
                user.setLastUpdatedDate(date);
                return user;
            }
        });

        when(userService.saveUser(any(User.class))).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = (User) invocation.getArguments()[0];
                User newUser = new User(user.getFirstName(), user.getLastName(), user.getUserId());
                newUser.setCreatedDate(date);
                newUser.setLastUpdatedDate(date);
                return newUser;
            }
        });

        when(userService.findUsersByLastName(anyString())).thenAnswer(new Answer<List<User>>() {
            @Override
            public List<User> answer(InvocationOnMock invocation) throws Throwable {
                String lastName = (String) invocation.getArguments()[0];
                User user1 = new User("Daryl", lastName, 401261859);
                user1.setCreatedDate(date);
                user1.setLastUpdatedDate(date);
                User user2 = new User("Cheryl", lastName, 401261860);
                user2.setCreatedDate(date);
                user2.setLastUpdatedDate(date);
                return Arrays.asList(user1, user2);
            }
        });

        //Note: we don't mock delete, as there's no point
    }
}
