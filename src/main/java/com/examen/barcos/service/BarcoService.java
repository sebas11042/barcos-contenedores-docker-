package com.examen.barcos.service;

import com.examen.barcos.entity.Barco;
import com.examen.barcos.repository.BarcoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarcoService {

    @Autowired
    private BarcoRepository barcoRepo;

    public List<Barco> listarTodos() {
        return barcoRepo.findAll();
    }

    public Barco guardar(Barco barco) {
        return barcoRepo.save(barco);
    }

    public Barco obtenerPorId(Long id) {
        return barcoRepo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        barcoRepo.deleteById(id);
    }
}
