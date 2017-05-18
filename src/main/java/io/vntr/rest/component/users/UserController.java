package io.vntr.rest.component.users;

import io.vntr.rest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by robertlindquist on 5/17/17.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public UserController() {
    }

    UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //CRUD Operations

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> saveUser(@RequestBody SaveUserDTO saveUserDTO) {
        User user = userMapper.fromSaveUserDTO(saveUserDTO);
        user.setUserId((int)(System.nanoTime() % 1000000000));
        Date now = new Date();
        user.setCreatedDate(now);
        user.setLastUpdatedDate(now);
        User savedUser = userService.saveUser(user);
        UserDTO userDTO = userMapper.fromEntity(savedUser);
        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = userMapper.fromEntity(user);
        return ResponseEntity.ok(userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id,
                                              @RequestBody SaveUserDTO saveUserDTO) {
        User oldUser = userService.getUserById(id);
        User user = userMapper.fromSaveUserDTO(saveUserDTO);
        user.setUserId(id);
        user.setCreatedDate(oldUser.getCreatedDate());
        user.setLastUpdatedDate(new Date());
        User savedUser = userService.saveUser(user);
        UserDTO userDTO = userMapper.fromEntity(savedUser);
        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }


    //Additional Operations

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value = "lastName",  required = false) String lastName,
                                                  @RequestParam(value = "firstName", required = false) String firstName) {
        List<User> users = null;
        if(firstName != null) {
            users = userService.findUsersByFirstName(firstName);
        }
        else if(lastName != null) {
            users = userService.findUsersByLastName(lastName);
        }
        List<UserDTO> userDTOs = userMapper.fromEntityList(users);
        return ResponseEntity.ok(userDTOs);
    }
}