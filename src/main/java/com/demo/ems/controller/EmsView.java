package com.demo.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.ems.entity.Employee;
import com.demo.ems.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmsView {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/index")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterPage(HttpServletRequest request,Model model){
        boolean result = employeeService.register(
            request.getParameter("name"),
            request.getParameter("username"),
            request.getParameter("password"),
            Employee.Role.USER
        );
        if(result){
            return "redirect:/login?register=true";
        }
        return "redirect:/register?error=true";
    }

}
