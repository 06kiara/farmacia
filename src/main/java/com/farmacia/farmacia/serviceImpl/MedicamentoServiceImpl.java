package com.farmacia.farmacia.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.repository.DetalleVentaRepository;
import com.farmacia.farmacia.repository.MedicamentoRepository;
import com.farmacia.farmacia.service.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

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

        if (detalleVentaRepository.existsByMedicamentoId(id)) {
            throw new RuntimeException(
                    "No se puede eliminar el medicamento porque tiene ventas registradas.");
        }

        medicamentoRepository.deleteById(id);
    }

    @Override
    public List<Medicamento> buscarPorNombre(String nombre) {
        return medicamentoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Integer obtenerStockTotal() {
        return medicamentoRepository.obtenerStockTotal();
    }
}