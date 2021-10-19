package bullsAndCows.service.impl;

import bullsAndCows.model.Rating;
import bullsAndCows.model.User;
import bullsAndCows.repository.RatingRepository;
import bullsAndCows.service.RatingService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@NoArgsConstructor
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating add(Rating rating) {
        return ratingRepository.saveAndFlush(rating);
    }

    @Override
    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating update(Rating rating) {
        return ratingRepository.saveAndFlush(rating);
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingByUser(User user) {
        return ratingRepository.getRatingByUser(user);
    }

    @Override
    public void freshRating(User user) {
        Rating rating = getRatingByUser(user);
        if(rating == null){
           this.add(new Rating(user, 1));
           return;
        }
        rating.setRecord(rating.getRecord() + 1);
        this.update(rating);
    }

    @Override
    public List<Rating> getRatingPlayer() {
        List<Rating> ratingList = ratingRepository.findAll();
        Collections.sort(ratingList);
        return ratingList;
    }
}
