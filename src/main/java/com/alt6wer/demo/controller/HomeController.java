package com.alt6wer.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
        
    @RequestMapping(value = "/")
    public ModelAndView index() {
        
        
        ModelAndView view = new ModelAndView("index");
        view.addObject("name", fab(7));
        return view;
    }
    
    private int fab(int index) {
        int x = 0;
        if (index == 0) {
            return x;
        }
        int y = 1;
        for(int i=2; i<=index; i++) {
            int temp = x + y;
            x = y;
            y = temp;
        }
        return y;
    }

}
