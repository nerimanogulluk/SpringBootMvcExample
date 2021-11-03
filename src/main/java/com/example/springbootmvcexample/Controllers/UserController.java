package com.example.springbootmvcexample.Controllers;

import com.example.springbootmvcexample.Services.UserService;
import com.example.springbootmvcexample.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/register" ,method= RequestMethod.GET)
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value="/register" ,method= RequestMethod.POST)
    public String saveRegisterPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);

        if (result.hasErrors()){
            return "register";
        } else {
            userService.saveUser(user);

        }
        return "index";
    }


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/index")
    public String index2(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String secure(){ return "secure";
    }
}
