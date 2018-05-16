package com.bays.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/page")
    public String errorPage(){
        return "404";
    }
}
