package com.farmacia.farmacia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmacia.farmacia.dto.CarritoItem;
import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.service.MedicamentoService;
import com.farmacia.farmacia.service.VentaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping("/ventas")
    public String listarVentas(Model model, HttpSession session) {

        // Historial de ventas
        model.addAttribute("ventas", ventaService.listarVentas());

        // Lista de medicamentos
        model.addAttribute("medicamentos",
                medicamentoService.listarMedicamentos());

        // Crear carrito si no existe
        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new ArrayList<CarritoItem>());
        }

        // Enviar carrito a la vista
        model.addAttribute("carrito", session.getAttribute("carrito"));

        return "ventas";
    }

    @PostMapping("/ventas/agregar")
    public String agregarAlCarrito(
            @RequestParam Long medicamento,
            @RequestParam Integer cantidad,
            HttpSession session) {

        @SuppressWarnings("unchecked")
        List<CarritoItem> carrito =
                (List<CarritoItem>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        Medicamento med =
                medicamentoService.obtenerMedicamentoPorId(medicamento);

        if (med != null) {

            CarritoItem item = new CarritoItem(med, cantidad);

            carrito.add(item);
        }

        session.setAttribute("carrito", carrito);

        return "redirect:/ventas";
    }
}