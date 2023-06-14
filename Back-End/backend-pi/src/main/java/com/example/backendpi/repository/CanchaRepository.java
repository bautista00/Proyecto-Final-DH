package com.example.backendpi.repository;

import com.example.backendpi.domain.Barrio;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    List<CanchaDTO> findByCategoria(Categoria categoria);
    List<CanchaDTO> findByUserEmail(String token);
//    List<CanchaDTO> FindByBarrioAndDeporte(Barrio barrio, Categoria categoria);
    @Query(value = "SELECT * FROM Cancha as ch INNER JOIN Domicilio as dom ON ch.domicilio_id = dom.id WHERE ch.categoria =:categoria AND dom.barrio = :barrio", nativeQuery = true)
    List<CanchaDTO> findCanchasByDeporteAndBarrio(@Param("categoria") Categoria categoria, @Param("barrio") Barrio barrio );
}
