package com.farmacia.farmacia.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    private Integer cantidad;

    private BigDecimal precio;

    private BigDecimal subtotal;

    // Constructor vacío
    public DetalleVenta() {
    }

    // Constructor con parámetros
    public DetalleVenta(Long id, Venta venta, Medicamento medicamento,
                        Integer cantidad, BigDecimal precio, BigDecimal subtotal) {
        this.id = id;
        this.venta = venta;
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}