package io.vntr.rest.component.users;

import io.vntr.rest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robertlindquist on 5/17/17.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = false)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = false)
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    public List<User> findUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public List<User> findUsersByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }
}
