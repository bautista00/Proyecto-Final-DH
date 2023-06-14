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
    @Query(value = "SELECT * FROM cancha as ch JOIN domicilio as dom ON ch.domicilio_id = dom.id JOIN barrio as br ON dom.barrio_id = br.id JOIN categoria as cat ON ch.categoria_id = cat.id WHERE cat.nombre = :categoria AND br.nombre = :barrio"
            , nativeQuery = true)
    List<Cancha> findCanchasByDeporteAndBarrio(@Param("categoria") String categoria, @Param("barrio") String barrio );
}
