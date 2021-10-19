package bullsAndCows.service.impl;

import bullsAndCows.model.GameStep;
import bullsAndCows.model.History;
import bullsAndCows.repository.GameStepRepository;
import bullsAndCows.service.GameStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameStepServiceImpl implements GameStepService {

    @Autowired
    private GameStepRepository gameStepRepository;

    @Override
    public GameStep add(GameStep gameStep) {
        return gameStepRepository.saveAndFlush(gameStep);
    }

    @Override
    public void deleteById(Long id) {
        gameStepRepository.deleteById(id);
    }

    @Override
    public GameStep update(GameStep gameStep) {
        return gameStepRepository.saveAndFlush(gameStep);
    }

    @Override
    public List<GameStep> findAll() {
        return gameStepRepository.findAll();
    }

    @Override
    public List<GameStep> getGameStepByHistory(History history) {
        return gameStepRepository.getGameStepByHistory(history);
    }
}
