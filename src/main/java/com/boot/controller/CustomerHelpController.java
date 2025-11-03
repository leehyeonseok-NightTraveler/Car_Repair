package com.boot.controller;

import com.boot.service.CustomerHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerHelpController {

    @Autowired
    private CustomerHelpService service;
}
