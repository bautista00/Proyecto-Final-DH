package com.example.backendpi.repository;

import com.example.backendpi.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
    Categoria findByNombre (String nombre);
}
