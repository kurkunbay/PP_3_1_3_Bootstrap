package com.example.springsecurity2.controller;

import com.example.springsecurity2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal User userAuth, Model model) {
        model.addAttribute("userAuth", userService.findByUsername(userAuth.getUsername()));
        return "user";
    }
}
