package com.example.backendpi.repository;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.User;
import com.example.backendpi.domain.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValoracionRepository extends JpaRepository<Valoracion,Long> {
    Valoracion findByUser(User user);

    List<Valoracion> findByCancha(Cancha cancha);

    Valoracion findByUserAndCancha(User user,Cancha cancha);
}
