package bullsAndCows.service;

import bullsAndCows.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User add(User user);
    boolean deleteById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> update(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    List<User> usergList(Long idMin);
}
