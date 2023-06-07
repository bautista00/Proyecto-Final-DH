package com.example.backendpi.repository;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.TurnoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByFecha(LocalDateTime fecha);
    TurnoDTO findByUser(User user);
    List<TurnoDTO> findByCancha(Cancha cancha);
}
