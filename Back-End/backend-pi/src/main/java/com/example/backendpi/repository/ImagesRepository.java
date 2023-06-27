package com.example.backendpi.repository;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.domain.Images;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images,Long> {

    Images findByCategoria(Categoria categoria) throws ResourceNotFoundException;
}
