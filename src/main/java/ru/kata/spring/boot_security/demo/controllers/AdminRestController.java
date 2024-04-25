package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserNotFoundException;
import ru.kata.spring.boot_security.demo.util.UserResponseError;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.showListUser(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> addUser(@RequestBody @Valid User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/userAuth")
    public ResponseEntity<User> showAuthUser() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getRoles")
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @ExceptionHandler
    private ResponseEntity<UserResponseError> handleException(UserNotFoundException e) {
        UserResponseError response = new UserResponseError("User not found with this id");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
