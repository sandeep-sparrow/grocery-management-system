package net.sandeep.grocery.store.repository;

import net.sandeep.grocery.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUserNameOrEmail(String username, String email);
    Boolean existsByUserName(String username);

}
