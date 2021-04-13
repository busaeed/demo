package com.alt6wer.demo.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.model.Role;
import com.alt6wer.demo.service.MemberService;
import com.alt6wer.demo.service.RoleService;*/

@Controller
public class HomeController {
    
    /*@Autowired
    private RoleService roleService;
    
    @Autowired
    private MemberService memberService;*/
    
    @RequestMapping(value = "/")
    public ModelAndView index() {
        /*Role role = new Role("ROLE_USER");
        Role createdRole = roleService.createRole(role);
        Member member = new Member("gant", "gant.32@gmail.com", "123456", createdRole);
        Member createdMember = memberService.createMember(member);*/
        
        ModelAndView view = new ModelAndView("index");
        view.addObject("result", "TEST"/*createdMember.toString()*/);
        return view;
    }

}
