package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Producto;
import com.farmacia.farmacia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // 🔵 LISTAR PRODUCTOS
    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("producto", new Producto()); // formulario vacío
        return "index";
    }

    // 🟢 GUARDAR O ACTUALIZAR (MISMO MÉTODO)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/";
    }

    // 🟡 CARGAR PRODUCTO EN FORMULARIO (EDITAR)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Producto producto = productoService.obtenerProductoPorId(id);

        model.addAttribute("producto", producto); // carga datos al form
        model.addAttribute("productos", productoService.listarProductos());

        return "index";
    }

    // 🔴 ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/";
    }
}