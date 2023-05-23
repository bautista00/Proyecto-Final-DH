package com.example.backendpi.repository;

import com.example.backendpi.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByFecha(LocalDateTime fecha);
}
