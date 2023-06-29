package com.example.backendpi.repository;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.CanchaDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
    List<Cancha> findByCategoria(Categoria categoria);
    List<Cancha> findByUser(User user);
//    List<CanchaDTO> FindByBarrioAndDeporte(Barrio barrio, Categoria categoria);
    @Query(value = "SELECT ch.user_id, br.nombre as nombrebarrio, categoria_id, domicilio_id, barrio_id, promedio_puntuacion, hora_apertura, hora_cierre, precioxhora, ch.id, ch.nombre, cat.nombre as nombrecategoria, dom.calle, dom.numero, dom.provincia, telefono,ch.descripcion, ch.images_id FROM cancha as ch " +
            "INNER JOIN domicilio as dom ON ch.domicilio_id = dom.id " +
            "INNER JOIN barrio as br ON dom.barrio_id = br.id " +
            "INNER JOIN categoria as cat ON ch.categoria_id = cat.id " +
//           "WHERE cat.nombre = :categoria AND br.nombre = :barrio ;"
             "WHERE cat.nombre = ?2 AND br.nombre = ?1 ;"
            , nativeQuery = true)
//    List<Cancha> findCanchasByDeporteAndBarrio(@Param("barrio") String barrio, @Param("categoria") String categoria );
      List<Cancha> findCanchasByDeporteAndBarrio(String barrio, String categoria );
    Cancha findByNombre(String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicio_cancha WHERE cancha_id = ?1", nativeQuery = true)
    void borrarCancha(Long id);






}
