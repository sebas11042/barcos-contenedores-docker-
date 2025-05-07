package com.examen.barcos.repository;

import com.examen.barcos.entity.Barco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcoRepository extends JpaRepository<Barco, Long> {
}
