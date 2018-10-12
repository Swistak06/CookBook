package com.swistak.CookBook.controller;

import com.swistak.CookBook.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @GetMapping("/signUp")
    public String loadSignUpPage(Model model){
        model.addAttribute("newUser",new UserDto());
        return "signUp";
    }

    @PostMapping("signUpUser")
    public String signUpUser(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult){
        System.out.println(userDto.getUsername());
        return "redirect:/";
    }
}
