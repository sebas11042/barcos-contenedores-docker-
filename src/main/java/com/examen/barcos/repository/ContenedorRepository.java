package com.examen.barcos.repository;

import com.examen.barcos.entity.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {

    List<Contenedor> findByBarcoId(Long barcoId);  // Para listar los contenedores de un barco
}
