package com.alt6wer.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alt6wer.demo.authentication.UserPrincipal;
import com.alt6wer.demo.component.Statistics;
import com.alt6wer.demo.model.Category;
import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.service.CategoryService;
import com.alt6wer.demo.service.MemberService;

@Controller
public class HomeController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private Statistics statistics;
    
    @GetMapping(value = "/")
    public String index(@AuthenticationPrincipal UserPrincipal principal, Model model) {
    	Member currentMember = memberService.findById(principal.getId());
    	List<Category> categories = categoryService.findAll();
    	statistics.init(); //update statistics because component are immutable by default
    	model.addAttribute("username", currentMember.getUsername());
    	model.addAttribute("categories", categories);
    	model.addAttribute("statistics", statistics);
		return "index";
    }

}
