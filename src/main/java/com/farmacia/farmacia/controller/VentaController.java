package com.farmacia.farmacia.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import com.farmacia.farmacia.entity.Venta;
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

        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("medicamentos", medicamentoService.listarMedicamentos());

        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new ArrayList<CarritoItem>());
        }

        @SuppressWarnings("unchecked")
        List<CarritoItem> carrito =
                (List<CarritoItem>) session.getAttribute("carrito");

        model.addAttribute("carrito", carrito);

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

            boolean existe = false;

            for (CarritoItem item : carrito) {

                if (item.getMedicamento().getId().equals(med.getId())) {

                    item.setCantidad(item.getCantidad() + cantidad);
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                carrito.add(new CarritoItem(med, cantidad));
            }
        }

        session.setAttribute("carrito", carrito);

        return "redirect:/ventas";
    }

    @PostMapping("/ventas/eliminar")
    public String eliminarDelCarrito(
            @RequestParam("indice") int indice,
            HttpSession session) {

        @SuppressWarnings("unchecked")
        List<CarritoItem> carrito =
                (List<CarritoItem>) session.getAttribute("carrito");

        if (carrito != null &&
                indice >= 0 &&
                indice < carrito.size()) {

            carrito.remove(indice);
        }

        session.setAttribute("carrito", carrito);

        return "redirect:/ventas";
    }

    @PostMapping("/ventas/confirmar")
    public String confirmarVenta(HttpSession session) {

        @SuppressWarnings("unchecked")
        List<CarritoItem> carrito =
                (List<CarritoItem>) session.getAttribute("carrito");

        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/ventas";
        }

        BigDecimal total = BigDecimal.ZERO;

        for (CarritoItem item : carrito) {

            Medicamento med = item.getMedicamento();

            med.setStock(med.getStock() - item.getCantidad());

            medicamentoService.guardarMedicamento(med);

            total = total.add(
                    med.getPrecio().multiply(
                            BigDecimal.valueOf(item.getCantidad())
                    )
            );
        }

        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(total);

        ventaService.guardarVenta(venta);

        carrito.clear();

        session.setAttribute("carrito", carrito);

        return "redirect:/ventas";
    }
}