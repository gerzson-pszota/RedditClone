package com.greenfoxacademy.reddit.Controllers;

import com.greenfoxacademy.reddit.Models.User;
import com.greenfoxacademy.reddit.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/identification")
    public String identification(User user) {
        return "identification";
    }

    @PostMapping("/signIn")
    public RedirectView signIn(@RequestParam String username, @RequestParam String password, User user, HttpSession session) {
        userService.signIn(username, password, user, session);
        return new RedirectView("/?username=" + username);
    }

    @PostMapping("/register")
    public RedirectView register(@RequestParam String username, @RequestParam String password, User user, HttpSession session) {
        userService.register(username, password, user, session);

        return new RedirectView("/?username=" + username);
    }

    @ControllerAdvice
    public class addUsernameAsParameter {

        @ModelAttribute("username")
        public String getUsername(HttpSession session) {
            return (String) session.getAttribute("username");
        }
    }

    @GetMapping("/signOut")
    public String signOut(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/";
    }
}