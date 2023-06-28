package com.example.backendpi.repository;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.domain.Images;
import com.example.backendpi.domain.Turno;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Images,Long> {

    Images findByCategoria(Categoria categoria) throws ResourceNotFoundException;

}
