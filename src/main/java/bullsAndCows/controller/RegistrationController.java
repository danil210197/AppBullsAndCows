package bullsAndCows.controller;

import bullsAndCows.model.History;
import bullsAndCows.model.Role;
import bullsAndCows.model.User;
import bullsAndCows.repository.UserRepository;
import bullsAndCows.service.RoleService;
import bullsAndCows.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/reg")
public class RegistrationController {

    private static final String EMPTY_VALUES = "Ошибка:Значение всех полей должны быть заполнены.";
    private static final String PASSWORDS_NOT_MATCH = "Ошибка:Пароли не совпадают.";
    private static final String USER_ALREADY_EXISTS = "Ошибка:Пользователь с таким логином уже существует.";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String reg(Model model) {
        model.addAttribute("user", new User());
        return "reg";
    }

    @PostMapping()
    public String reg(@ModelAttribute("user") User user,
                      Model model) {

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getPasswordConfirm().isEmpty()) {
            model.addAttribute("errorAnswer", EMPTY_VALUES);
            return "reg";
        } else if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("errorAnswer", PASSWORDS_NOT_MATCH);
            return "reg";
        } else if (userService.findByLogin(user.getLogin()).isPresent()) {
            model.addAttribute("errorAnswer", USER_ALREADY_EXISTS);
            return "reg";
        } else {
            user.setRoles(Collections.singletonList(new Role(1L, "ROLE_USER")));
            userService.add(user);
            return "redirect:login";
        }

    }

    /*
    @GetMapping()
    public String reg(Model model) {
        return "reg";
    }


    @PostMapping()
    public String reg(@RequestParam("login") String login,
                      @RequestParam("password") String password,
                      @RequestParam("confirmPassword") String confirmPassword,
                      Model model) {


        if (login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            model.addAttribute("errorAnswer", EMPTY_VALUES);
            return "reg";
        } else if (!password.equals(confirmPassword)) {
            model.addAttribute("errorAnswer", PASSWORDS_NOT_MATCH);
            return "reg";
        } else if (userService.findByLogin(login).isPresent()) {
            model.addAttribute("errorAnswer", USER_ALREADY_EXISTS);
            return "reg";
        } else {
            User newUser = User.builder()
                    .login(login)
                    .password(password)
                    .roles(Collections.singletonList(new Role(1L, "ROLE_USER")))
                    .build();

            userService.add(newUser);
            return "redirect:login";
        }

    }

     */

}
