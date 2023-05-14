package com.example.backendpi.repository;

import com.example.backendpi.domain.Admin;
import com.example.backendpi.domain.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
}
