package com.example.backendpi.repository;

import com.example.backendpi.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    Servicio findByNombre(String nombre);
}
