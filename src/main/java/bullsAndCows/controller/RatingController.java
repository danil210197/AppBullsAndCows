package bullsAndCows.controller;

import bullsAndCows.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public String rating(Model model){
        model.addAttribute("ratings", ratingService.getRatingPlayer());
        return "rating";
    }

}
