package com.alt6wer.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class authentication {
    
    @RequestMapping(value = "/register")
    public ModelAndView register() {
        
        ModelAndView view = new ModelAndView("register");
        return view;
    }
    
    @RequestMapping(value = "/login")
    public ModelAndView logIn() {
        
        ModelAndView view = new ModelAndView("login");
        return view;
    }

}
