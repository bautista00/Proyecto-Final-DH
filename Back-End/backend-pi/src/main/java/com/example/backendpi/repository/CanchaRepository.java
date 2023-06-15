package com.example.backendpi.repository;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    List<CanchaDTO> findByCategoria(Categoria categoria);
    List<CanchaDTO> findByUserEmail(String token);
//    List<CanchaDTO> FindByBarrioAndDeporte(Barrio barrio, Categoria categoria);
    @Query(value = "SELECT user_id, br.nombre as nombrebarrio, categoria_id, domicilio_id, barrio_id, promedio_puntuacion, hora_apertura, hora_cierre, precioxhora, puntuacion, ch.id, ch.nombre, cat.nombre as nombrecategoria, dom.calle, dom.numero, dom.provincia, telefono FROM cancha as ch \n" +
            "INNER JOIN domicilio as dom ON ch.domicilio_id = dom.id \n" +
            "INNER JOIN barrio as br ON dom.barrio_id = br.id \n" +
            "INNER JOIN categoria as cat ON ch.categoria_id = cat.id \n" +
            "WHERE cat.nombre = :categoria AND br.nombre = :barrio ;"
            , nativeQuery = true)
    List<Cancha> findCanchasByDeporteAndBarrio(@Param("barrio") String barrio, @Param("categoria") String categoria );
}
