package com.alt6wer.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
        
    @RequestMapping(value = "/test")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        view.addObject("name", "test");
        return view;
    }

}
