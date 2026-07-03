package com.farmacia.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.farmacia.farmacia.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}