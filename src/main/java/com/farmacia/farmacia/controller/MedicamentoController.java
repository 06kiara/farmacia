package com.farmacia.farmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.service.MedicamentoService;
import com.farmacia.farmacia.service.VentaService;

@Controller
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private VentaService ventaService;

    // LISTAR MEDICAMENTOS
    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());
        model.addAttribute("medicamento", new Medicamento());
        model.addAttribute("totalVentas", ventaService.contarVentas());
        model.addAttribute("stockTotal", medicamentoService.obtenerStockTotal());

        return "index";
    }

    // BUSCAR MEDICAMENTOS
    @GetMapping("/buscar")
    public String buscar(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("medicamentos", medicamentoService.buscarPorNombre(nombre));
        model.addAttribute("medicamento", new Medicamento());
        model.addAttribute("totalVentas", ventaService.contarVentas());
        model.addAttribute("stockTotal", medicamentoService.obtenerStockTotal());

        return "index";
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
        model.addAttribute("totalVentas", ventaService.contarVentas());
        model.addAttribute("stockTotal", medicamentoService.obtenerStockTotal());

        return "index";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            medicamentoService.eliminarMedicamento(id);
            redirectAttributes.addFlashAttribute("success",
                    "Medicamento eliminado correctamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error",
                    e.getMessage());
        }

        return "redirect:/";
    }
}