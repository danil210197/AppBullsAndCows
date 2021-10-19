package bullsAndCows.service;

import bullsAndCows.model.GameStep;
import bullsAndCows.model.History;
import bullsAndCows.model.User;

import java.util.List;

public interface GameStepService {

    GameStep add(GameStep gameStep);
    void deleteById(Long id);
    GameStep update(GameStep gameStep);
    List<GameStep> findAll();
    List<GameStep> getGameStepByHistory(History history);
}
