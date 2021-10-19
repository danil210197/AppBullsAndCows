package bullsAndCows.service;

import bullsAndCows.model.GameStep;
import bullsAndCows.model.Rating;
import bullsAndCows.model.User;

import java.util.List;

public interface RatingService {

    Rating add(Rating rating);
    void deleteById(Long id);
    Rating update(Rating rating);
    List<Rating> findAll();
    Rating getRatingByUser(User user);
    void freshRating(User user);
    List<Rating> getRatingPlayer();
}
