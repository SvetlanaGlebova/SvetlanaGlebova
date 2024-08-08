package com.example.docdelivery.controller;

import com.example.docdelivery.entity.ApplicationUser;
import com.example.docdelivery.service.AccountService;
import com.example.docdelivery.service.RentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final RentorService rentorService;

    @GetMapping("/registration")
    public String registration(ApplicationUser applicationUser) {
        return "account/registration";
    }

    /*@GetMapping("/login")
    public String login() {
        return "account/login";
    }*/

    @PostMapping("/registration")
    public String createAccount(ApplicationUser user, Model model) {
        try {
            accountService.registration(user);
            rentorService.createRentor(user);
            return "redirect:/login";
        } catch (AccountException e) {
            model.addAttribute("error", e.getMessage());
            return "account/registration";
        }
    }

    /* @ResponseBody
    @PostMapping("/login")
    public Token loginAccount(@RequestParam("application_user_username") String username,
                              @RequestParam("application_user_password") String password) {
        try {
            return accountService.loginAccount(username, password);
        } catch (AccountException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }*/

}

