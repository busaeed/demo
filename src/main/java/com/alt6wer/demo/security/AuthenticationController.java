package com.alt6wer.demo.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
    
    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView view = new ModelAndView("login");
        return view;
    }

}
