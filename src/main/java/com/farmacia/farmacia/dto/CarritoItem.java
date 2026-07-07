package com.farmacia.farmacia.dto;

import java.math.BigDecimal;

import com.farmacia.farmacia.entity.Medicamento;

public class CarritoItem {

    private Medicamento medicamento;
    private Integer cantidad;

    public CarritoItem() {
    }

    public CarritoItem(Medicamento medicamento, Integer cantidad) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    // Calcula el subtotal automáticamente
    public BigDecimal getSubtotal() {
        return medicamento.getPrecio()
                .multiply(BigDecimal.valueOf(cantidad));
    }

}