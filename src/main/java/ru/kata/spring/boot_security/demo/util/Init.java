package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void createRolesAndBasicUsers() {
        if (roleService.getAllRoles().isEmpty() || userService.getAllUsers().isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            Set<Role> adminSet = new HashSet<>();
            Set<Role> userSet = new HashSet<>();

            roleService.addRole(roleAdmin);
            roleService.addRole(roleUser);

            adminSet.add(roleAdmin);
            adminSet.add(roleUser);
            userSet.add(roleUser);

            User admin = new User("admin",  "adminov",
                    "100", 25, "admin@mail.ru", adminSet);
            User user = new User("user",  "userov",
                    "100", 45, "user@mail.ru", userSet);

            userService.add(user);
            userService.add(admin);
        }
    }
}