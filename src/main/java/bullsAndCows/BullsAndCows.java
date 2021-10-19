package bullsAndCows;

import bullsAndCows.model.Rating;
import bullsAndCows.model.Role;
import bullsAndCows.model.User;
import bullsAndCows.service.impl.RoleServiceImpl;
import bullsAndCows.service.impl.UserServiceImp;
import bullsAndCows.service.RoleService;
import bullsAndCows.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class BullsAndCows {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BullsAndCows.class, args);

        Role userRole = new Role(1L, "ROLE_USER");
        Role adminRole = new Role(2L, "ROLE_ADMIN");

        RoleService roleService = context.getBean(RoleServiceImpl.class);
        roleService.add(userRole);
        roleService.add(adminRole);

        UserService userService = context.getBean(UserServiceImp.class);
        User admin = new User("admin", "123", Collections.singletonList(adminRole));
        userService.add(admin);
        User user = new User("user", "123", Collections.singletonList(userRole));
        userService.add(user);
    }
}
