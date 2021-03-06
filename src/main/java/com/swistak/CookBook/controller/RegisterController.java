package com.swistak.CookBook.controller;

import com.swistak.CookBook.dto.UserDto;
import com.swistak.CookBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String loadSignUpPage(Model model){
        model.addAttribute("newUser",new UserDto());
        return "signUp";
    }

    @PostMapping("signUpUser")
    public String signUpUser(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult){
        userService.registerUser(userDto);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/api/checkIfUserExist/{username}")
    public boolean checkIfUsernameIsInUse(@PathVariable("username") String username){
        return userService.findByUsername(username) != null;
    }
}
