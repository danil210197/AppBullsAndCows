package bullsAndCows.repository;

import bullsAndCows.model.GameStep;
import bullsAndCows.model.History;
import bullsAndCows.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameStepRepository extends JpaRepository<GameStep, Long> {

    List<GameStep> getGameStepByHistory(History history);

}
