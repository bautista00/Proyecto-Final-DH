package com.example.backendpi.repository;

import com.example.backendpi.domain.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorRepository extends JpaRepository<Prestador, Long> {
}
