package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;

@Controller
@RequestMapping("/users")
public class userController {
    @PostMapping
    public String createUser(@RequestParam("name") String name,@RequestParam("email") String email,
                            @RequestParam("age") int age){
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        userService.save(user);
        return ("redirect:/users");
    }

    @GetMapping
    public String getListUsers(Model model){
        return "listUsers";
    }

    @GetMapping("/id")
    public String show(@RequestParam(value = "id" , required = false) int id, Model model){
        return null;
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user",new User());
        return "new";
    }

}
