package com.example.backendpi.repository;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    List<Cancha> findByDeporteAndBarrio(Deporte Deporte, String Barrio);


}
