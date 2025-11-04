package com.boot.controller;

import com.boot.service.CustomerHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerHelpController {

    @Autowired
    private CustomerHelpService service;
    
    @GetMapping("/main")
    public String showMainPage() {
        // ViewResolver가 /WEB-INF/views/main.jsp로 매핑함
        return "main";
    }
}
