package ru.kata.spring.boot_security.demo.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;



@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private  final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String newUser(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("user") @Valid User user
            , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        if(userService.save(user)){
            return "login";
        }else {return "redirect:/registration";}
    }
}
