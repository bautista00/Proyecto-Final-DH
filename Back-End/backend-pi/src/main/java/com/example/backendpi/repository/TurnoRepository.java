package com.example.backendpi.repository;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.TurnoDTO;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    Turno findByFechaAndCancha(DateTime fecha, Cancha cancha);
    Turno findByUser(User user);
    List<Turno> findByCancha(Cancha cancha);

}
