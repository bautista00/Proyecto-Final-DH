package com.example.backendpi.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    private Long id;
    private String nombre;
    private String url;
    private String descripcion;

}
