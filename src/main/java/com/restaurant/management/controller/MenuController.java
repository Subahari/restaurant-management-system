package com.restaurant.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/manage")
public class MenuController {

    @GetMapping("/addmenu")
    public String adminmenu(){
        return "addmenu";
    }
}
