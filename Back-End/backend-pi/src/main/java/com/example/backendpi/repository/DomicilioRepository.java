package com.example.backendpi.repository;

import com.example.backendpi.domain.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
    Domicilio findByCalle(String calle);

}
