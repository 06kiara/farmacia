package com.farmacia.farmacia.service;

import java.util.List;

import com.farmacia.farmacia.entity.Medicamento;

public interface MedicamentoService {

    List<Medicamento> listarMedicamentos();

    Medicamento guardarMedicamento(Medicamento medicamento);

    Medicamento obtenerMedicamentoPorId(Long id);

    // ESTE MÉTODO FALTABA
    Medicamento actualizarMedicamento(Medicamento medicamento);

    void eliminarMedicamento(Long id);

    // Buscar medicamentos
    List<Medicamento> buscarPorNombre(String nombre);
}