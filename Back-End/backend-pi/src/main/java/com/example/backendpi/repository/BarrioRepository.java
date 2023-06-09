package com.example.backendpi.repository;

import com.example.backendpi.domain.Barrio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarrioRepository extends JpaRepository<Barrio,Long> {
    Barrio findByNombre(String nombre);

}



