package com.farmacia.farmacia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.farmacia.farmacia.dto.CarritoItem;
import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.service.MedicamentoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping("/agregar/{id}")
    public String agregar(@PathVariable Long id,
                          HttpSession session) {

        List<CarritoItem> carrito =
                (List<CarritoItem>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        Medicamento medicamento =
                medicamentoService.obtenerMedicamentoPorId(id);

        boolean existe = false;

        for (CarritoItem item : carrito) {

            if (item.getMedicamento().getId().equals(id)) {

                item.setCantidad(item.getCantidad() + 1);
                existe = true;
                break;
            }
        }

        if (!existe) {

            carrito.add(new CarritoItem(medicamento, 1));

        }

        session.setAttribute("carrito", carrito);

        return "redirect:/";
    }

}