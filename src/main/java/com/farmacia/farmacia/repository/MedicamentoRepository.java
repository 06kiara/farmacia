package com.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.entity.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT COALESCE(SUM(m.stock), 0) FROM Medicamento m")
    Integer obtenerStockTotal();

}