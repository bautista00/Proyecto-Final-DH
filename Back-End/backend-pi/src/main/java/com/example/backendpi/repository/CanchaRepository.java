package com.example.backendpi.repository;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Deporte;
import com.example.backendpi.dto.CanchaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    List<CanchaDTO> findByDeporte(Deporte deporte);
    List<CanchaDTO> findByOwner(String token);
}
