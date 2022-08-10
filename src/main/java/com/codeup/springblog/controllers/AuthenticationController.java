package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.services.UserDetailsLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    private PasswordEncoder pe;
    private UserDetailsLoader udl;

    public AuthenticationController(PasswordEncoder passwordEncoder, UserDetailsLoader userDetailsLoader){
        this.pe = passwordEncoder;
        this.udl = userDetailsLoader;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

//    @GetMapping("/logout")
//    public String logout(){
//        return "users/login";
//    }
}