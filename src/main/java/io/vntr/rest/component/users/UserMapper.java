package io.vntr.rest.component.users;

import io.vntr.rest.component.DateTimeDTO;
import io.vntr.rest.component.DateTimeMapper;
import io.vntr.rest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertlindquist on 5/17/17.
 */
@Component
public class UserMapper {

    @Autowired
    private DateTimeMapper dateTimeMapper;

    public UserMapper() {

    }

    UserMapper(DateTimeMapper dateTimeMapper) {
        this.dateTimeMapper = dateTimeMapper;
    }

    public UserDTO fromEntity(User user) {
        if(user == null) {
            return null;
        }
        DateTimeDTO created = dateTimeMapper.fromEntity(user.getCreatedDate());
        DateTimeDTO lastUpdated = dateTimeMapper.fromEntity(user.getLastUpdatedDate());
        UserDTO userDTO = new UserDTO(user.getUserId(), user.getFirstName(), user.getLastName(), created, lastUpdated);
        return userDTO;
    }

    public User fromSaveUserDTO(SaveUserDTO saveUserDTO) {
        if(saveUserDTO == null) {
            return null;
        }
        User user = new User(saveUserDTO.getFirstName(), saveUserDTO.getLastName(), null);
        return user;
    }

    public List<UserDTO> fromEntityList(List<User> users) {
        if(users == null) {
            return null;
        }
        List<UserDTO> dtoList = new ArrayList<>(users.size()+1);
        for(int i=0; i<users.size(); i++) {
            dtoList.add(fromEntity(users.get(i)));
        }
        return dtoList;
    }
}
