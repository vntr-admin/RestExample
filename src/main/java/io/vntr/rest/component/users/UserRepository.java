package io.vntr.rest.component.users;

import io.vntr.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by robertlindquist on 5/17/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);
}
