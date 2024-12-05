package com.example.demo.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.MyAppUserService;

@Controller
public class ContentController {

    @Autowired
    private MyAppUserService myAppUserService;
    
    @GetMapping("/req/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/req/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/index")
    public String home(org.springframework.ui.Model model, Principal principal) {
        String username = principal.getName();
        MyAppUser user = myAppUserService.findByUsername(username);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "index";
    }
    
}
