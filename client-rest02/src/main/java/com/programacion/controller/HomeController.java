package com.programacion.controller;

import com.programacion.models.Singer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String hello() {
        return "index";
    }

    @RequestMapping("/thymeleaf")
    public String thyme(Model model) {
        model.addAttribute("singer", new Singer());
        return "thyme";
    }
}