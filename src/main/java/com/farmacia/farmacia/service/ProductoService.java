package com.farmacia.farmacia.service;

import java.util.List;

import com.farmacia.farmacia.entity.Producto;

public interface ProductoService {

    List<Producto> listarProductos();

    Producto guardarProducto(Producto producto);

    Producto obtenerProductoPorId(Long id);

    Producto actualizarProducto(Producto producto);

    void eliminarProducto(Long id);
}