package com.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.entity.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    // Buscar medicamentos por nombre (ignorando mayúsculas y minúsculas)
    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);

}