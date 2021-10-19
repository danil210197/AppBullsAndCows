package bullsAndCows.repository;

import bullsAndCows.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        @Query("select u from User u where u.login = :login")
        User findByLogin(@Param("login") String login);

        @Query("select u from User u where u.id > :userId")
        List<User> usergList(@Param("userId") Long idMin);
}
