package bullsAndCows.controller;

import bullsAndCows.model.History;
import bullsAndCows.model.User;
import bullsAndCows.service.GameStepService;
import bullsAndCows.service.HistoryService;
import bullsAndCows.valid.NoRepeatNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;


@Validated
@Controller
public class GameController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private GameStepService gameStepService;


    @PostMapping("/newGame")
    public String newGame(@AuthenticationPrincipal User user, Model model) {

        History history = historyService.add(historyService.createHistory(user));
        model.addAttribute("login", user.getLogin());
        model.addAttribute("history", history);
        model.addAttribute("gameSteps", history.getGameStep());
        return "game";
    }

    @GetMapping("/proceed")
    public String proceed(@RequestParam("historyId") String historyId,
                          @AuthenticationPrincipal User user,
                          Model model){

        History history = historyService.getById(Long.valueOf(historyId));
        model.addAttribute("login", user.getLogin());
        model.addAttribute("history", history);
        model.addAttribute("gameSteps", history.getGameStep());
        return "game";
    }

    @PostMapping("/stepUser")
    public String stepUser(@AuthenticationPrincipal User user,
                           @RequestParam("stepUser") @NoRepeatNumbers  int stepUser,
                           Model model){

        History currentHistory = historyService.findByUserLastHistory(user);
        model.addAttribute("login", user.getLogin());
        model.addAttribute("history", currentHistory);

        model.addAttribute("gameSteps", historyService.getFreshGameStep(currentHistory, stepUser));
        return "game";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleConstraintViolationException(ConstraintViolationException e, Model model,
                                              @AuthenticationPrincipal User user) {
        History currentHistory = historyService.findByUserLastHistory(user);
        model.addAttribute("login", user.getLogin());
        model.addAttribute("history", currentHistory);
        model.addAttribute("errorAnswer", "Число не должно содержать повторяющихся чисел");
        model.addAttribute("gameSteps", gameStepService.getGameStepByHistory(currentHistory));
        return "game";
    }

}
