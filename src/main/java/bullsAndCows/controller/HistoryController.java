package bullsAndCows.controller;

import bullsAndCows.model.History;
import bullsAndCows.model.User;
import bullsAndCows.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public String history(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("histories", historyService.findByUserAndStartDateSort(user));
        return "history";
    }



}
