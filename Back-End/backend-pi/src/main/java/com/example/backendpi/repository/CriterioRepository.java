package com.example.backendpi.repository;


import com.example.backendpi.domain.Criterios;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CriterioRepository extends JpaRepository<Criterios, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM criterios WHERE cancha_id = ?1", nativeQuery = true)
    void borrarPorCanchaID(Long id);
}
