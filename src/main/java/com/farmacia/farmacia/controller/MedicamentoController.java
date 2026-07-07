package com.farmacia.farmacia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.service.MedicamentoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    // LISTAR MEDICAMENTOS
    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
        model.addAttribute("medicamento", new Medicamento());
        return "index";
    }

    // BUSCAR MEDICAMENTOS
    @GetMapping("/buscar")
    public String buscar(@RequestParam("nombre") String nombre, Model model) {

        model.addAttribute("medicamentos", medicamentoService.buscarPorNombre(nombre));
        model.addAttribute("medicamento", new Medicamento());

        return "index";
    }

    // ==========================
    // AGREGAR AL CARRITO
    // ==========================
    @GetMapping("/carrito/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id, HttpSession session) {

        Medicamento medicamento = medicamentoService.obtenerMedicamentoPorId(id);

        List<Medicamento> carrito =
                (List<Medicamento>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        carrito.add(medicamento);

        session.setAttribute("carrito", carrito);

        return "redirect:/";
    }

    // ==========================
    // VER CARRITO
    // ==========================
    @GetMapping("/carrito")
    public String verCarrito(HttpSession session, Model model) {

        List<Medicamento> carrito =
                (List<Medicamento>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        model.addAttribute("carrito", carrito);

        return "carrito";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Medicamento medicamento) {
        medicamentoService.guardarMedicamento(medicamento);
        return "redirect:/";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Medicamento medicamento = medicamentoService.obtenerMedicamentoPorId(id);

        model.addAttribute("medicamento", medicamento);
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());

        return "index";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        medicamentoService.eliminarMedicamento(id);
        return "redirect:/";
    }
}