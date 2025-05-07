package com.examen.barcos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    @GetMapping("/")
    public String redirigirInicio() {
        return "redirect:/barcos";
    }
    



}
