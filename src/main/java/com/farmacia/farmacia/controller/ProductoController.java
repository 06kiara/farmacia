package com.farmacia.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

}