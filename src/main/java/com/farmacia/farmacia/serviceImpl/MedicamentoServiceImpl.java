package com.farmacia.farmacia.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.repository.MedicamentoRepository;
import com.farmacia.farmacia.service.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento guardarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public Medicamento obtenerMedicamentoPorId(Long id) {
        return medicamentoRepository.findById(id).orElse(null);
    }

    @Override
    public Medicamento actualizarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public void eliminarMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }

    // NUEVO MÉTODO PARA BUSCAR
    @Override
    public List<Medicamento> buscarPorNombre(String nombre) {
        return medicamentoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}