package bullsAndCows.repository;

import bullsAndCows.model.Rating;
import bullsAndCows.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

      Rating getRatingByUser(User user);
}
