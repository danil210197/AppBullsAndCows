package bullsAndCows.service;

import bullsAndCows.model.GameStep;
import bullsAndCows.model.History;
import bullsAndCows.model.Rating;
import bullsAndCows.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    History add(History history);
    void deleteById(Long id);
    History update(History history);
    List<History> findAll();
    List<History> findByUserAndStartDateSort(User user);
    List<History> findByIsWin(boolean isWin);
    History findByUserLastHistory(User User);
    long getCountWinHistoryByUser(User user);
    History createHistory(User user);
    List<History> getHistoryByUser(User user);
    List<GameStep> getFreshGameStep(History currentHistory, int stepUser);
    long getCountGameSteps(History history);
    History getById(Long id);
}
