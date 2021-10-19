package bullsAndCows.service.impl;

import bullsAndCows.model.GameStep;
import bullsAndCows.model.History;
import bullsAndCows.model.User;
import bullsAndCows.model.GameResultEnum;
import bullsAndCows.repository.HistoryReposotory;
import bullsAndCows.service.GameStepService;
import bullsAndCows.service.HistoryService;
import bullsAndCows.service.RatingService;
import bullsAndCows.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryReposotory historyReposotory;

    @Autowired
    private GameStepService gameStepService;

    @Autowired
    private RatingService ratingService;

    @Override
    public History add(History history) {
        return historyReposotory.saveAndFlush(history);
    }

    @Override
    public void deleteById(Long id) {
        historyReposotory.deleteById(id);
    }

    @Override
    public History update(History history) {
        return historyReposotory.saveAndFlush(history);
    }

    @Override
    public List<History> findAll() {
        return historyReposotory.findAll();
    }

    @Override
    public List<History> findByUserAndStartDateSort(User user) {
        return historyReposotory.findByUserAndStartDateSort(user,
                Sort.by(Sort.Direction.ASC, "startDate"));
    }

    @Override
    public List<History> findByIsWin(boolean isWin) {
        return historyReposotory.findByIsWin(isWin);
    }

    @Override
    public History findByUserLastHistory(User user) {
        Pageable firstPage = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "endDate"));
        return historyReposotory.findByUserLastHistory(user, firstPage).get(0);
    }

    @Override
    public long getCountWinHistoryByUser(User user) {
        return historyReposotory.getCountWinHistoryByUser(user.getId());
    }

    @Override
    public History createHistory(User user) {
        return History.builder()
                .user(user)
                .number(this.generateNumber())
                .startDate(new GregorianCalendar())
                .endDate(new GregorianCalendar())
                .steps(0)
                .isWin(GameResultEnum.NO).build();
    }

    @Override
    public List<History> getHistoryByUser(User user) {
        return historyReposotory.getHistoriesByUser(user);
    }


    private int generateNumber() {
        List<String> numbers = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Collections.shuffle(numbers);
        if (numbers.indexOf("0") == 0) {
            numbers.set(0, numbers.get(1));
            numbers.set(1, "0");
        }

        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iterator = numbers.iterator();
        while (stringBuilder.length() != 4 && iterator.hasNext()) {
            stringBuilder.append(iterator.next());
        }

        return Integer.parseInt(stringBuilder.toString());
    }


    private BullsAndCows getCowsAndBullsCount(int stepUser, int hiddenNumber) {
        char[] answer = String.valueOf(stepUser).toCharArray();
        char[] hiddenNum = String.valueOf(hiddenNumber).toCharArray();

        int cows = 0, bulls = 0;

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < hiddenNum.length; j++) {
                if (answer[i] == hiddenNum[j] && i == j) {
                    ++bulls;
                } else if (answer[i] == hiddenNum[j]) {
                    ++cows;
                }
            }
        }
        return new BullsAndCows(bulls, cows);
    }

    private String getAnswerComputer(History currentHistory, int stepUser) {
        BullsAndCows bullsAndCows = getCowsAndBullsCount(stepUser, currentHistory.getNumber());

        return String.format(
                currentHistory.getNumber() == stepUser
                        ? "Вы угадали! %sБ%sK"
                        : "%sБ%sK",
                bullsAndCows.getBulls(),
                bullsAndCows.getCows());
    }

    @Override
    public List<GameStep> getFreshGameStep(History currentHistory, int stepUser) {

        GameStep gameStep = GameStep.builder()
                .stepUser(stepUser)
                .answer(this.getAnswerComputer(currentHistory, stepUser))
                .history(currentHistory).build();
        gameStepService.add(gameStep);


        currentHistory.setSteps(this.getCountGameSteps(currentHistory));
        if(stepUser == currentHistory.getNumber()){
            currentHistory.setIsWin(GameResultEnum.YES);
            ratingService.freshRating(currentHistory.getUser());
        }
        this.update(currentHistory);

        return currentHistory.getGameStep();
    }

    @Override
    public long getCountGameSteps(History history) {
        return historyReposotory.getCountGameSteps(history);
    }

    @Override
    public History getById(Long id) {
        History history = historyReposotory.getByHistoryId(id);
        return historyReposotory.getByHistoryId(id);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class BullsAndCows {
        private int bulls;
        private int cows;
    }
}
