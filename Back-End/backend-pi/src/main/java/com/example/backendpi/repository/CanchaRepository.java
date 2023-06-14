package com.example.backendpi.repository;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    List<CanchaDTO> findByCategoria(Categoria categoria);
    List<CanchaDTO> findByUserEmail(String token);


}
