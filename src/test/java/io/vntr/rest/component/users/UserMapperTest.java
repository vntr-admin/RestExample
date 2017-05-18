package io.vntr.rest.component.users;

import io.vntr.rest.TestUtils;
import io.vntr.rest.component.DateTimeDTO;
import io.vntr.rest.component.DateTimeMapper;
import io.vntr.rest.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class UserMapperTest {

    private UserMapper userMapper;

    @Before
    public void init() {
        userMapper = new UserMapper(new DateTimeMapper());
    }

    @Test
    public void testFromEntity() {
        User user = new User("Daryl", "Hannah", 123);
        user.setCreatedDate(TestUtils.getDateChicago(2017, 1, 15, 19, 30));

        DateTimeDTO createdDTO = new DateTimeDTO("01/15/2017", "07:30 PM", "2017-01-15T19:30");
        UserDTO expectedResult = new UserDTO(123, "Daryl", "Hannah", createdDTO, null);
        UserDTO result = userMapper.fromEntity(user);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFromSaveUserDTO() {
        //TODO: do this
    }
}
