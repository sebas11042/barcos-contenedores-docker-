package com.examen.barcos.service;

import com.examen.barcos.entity.Contenedor;
import com.examen.barcos.repository.ContenedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenedorService {

    @Autowired
    private ContenedorRepository contenedorRepo;

    public List<Contenedor> listarTodos() {
        return contenedorRepo.findAll();
    }

    public List<Contenedor> listarPorBarco(Long barcoId) {
        return contenedorRepo.findByBarcoId(barcoId);
    }

    public Contenedor guardar(Contenedor contenedor) {
        return contenedorRepo.save(contenedor);
    }

    public Contenedor obtenerPorId(Long id) {
        return contenedorRepo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        contenedorRepo.deleteById(id);
    }
}
