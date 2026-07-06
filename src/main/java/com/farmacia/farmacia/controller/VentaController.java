package com.farmacia.farmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.farmacia.farmacia.service.VentaService;

@Controller
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/ventas")
    public String listarVentas(Model model) {

        model.addAttribute("ventas", ventaService.listarVentas());

        return "ventas";
    }
}