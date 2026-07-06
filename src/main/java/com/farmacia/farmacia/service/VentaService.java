package com.farmacia.farmacia.service;

import java.util.List;

import com.farmacia.farmacia.entity.Venta;

public interface VentaService {

    // Listar todas las ventas
    List<Venta> listarVentas();

    // Guardar una venta
    Venta guardarVenta(Venta venta);

    // Buscar una venta por ID
    Venta obtenerVentaPorId(Long id);
}
