package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepositories;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class Init {
    private UserRepositories userRepositories;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepositories userRepositories, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;

        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void runObjectCreated() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        List roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(userRole);

        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        userRepositories.save(new User("roma", passwordEncoder.encode("123")
                , "roma@mail.com", List.of(adminRole)));
        userRepositories.save(new User("ivan", passwordEncoder.encode("456")
                , "ivan@mail.com", List.of(userRole)));
        userRepositories.save(new User("ira", passwordEncoder.encode("789")
                , "ira@mail.com", roles));

    }
}
