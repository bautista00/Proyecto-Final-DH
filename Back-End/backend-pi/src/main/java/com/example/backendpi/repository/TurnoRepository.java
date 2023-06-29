package com.example.backendpi.repository;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.TurnoDTO;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    Turno findByFechaAndCancha(LocalDateTime fecha, Cancha cancha);
    List<Turno> findByUser(User user);
    List<Turno> findByCancha(Cancha cancha);
    @Query(value = "SELECT * FROM turno as tr WHERE tr.fecha between '2000-01-01 12:59' AND CURDATE() AND tr.user_id =?;"
            , nativeQuery = true)
    List<Turno> findByUserWithFecha(Long id);

    @Query(value = "SELECT * FROM turno as tr WHERE tr.fecha between CURDATE() AND '3000-01-01 12:59' AND tr.cancha_id =?;"
            , nativeQuery = true)
    List<Turno> findByCanchaWithFecha(Long id);

    @Query(value = "SELECT * FROM turno as tr WHERE tr.fecha between '2000-01-01 12:59' AND CURDATE() AND tr.cancha_id =?;"
            , nativeQuery = true)
    List<Turno> findByCanchaWithFechaVencido(Long id);


    @Query(value = "SELECT * FROM turno as tr WHERE tr.cancha_id = ? ;"
            , nativeQuery = true)
    List<Turno> findByCancha2(Long id);







    //query que nos traiga todos los turnos desde del -infinito hasta el dia de hoy


}
