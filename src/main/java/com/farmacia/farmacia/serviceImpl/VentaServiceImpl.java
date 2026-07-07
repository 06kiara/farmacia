package com.farmacia.farmacia.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.VentaRepository;
import com.farmacia.farmacia.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }
}