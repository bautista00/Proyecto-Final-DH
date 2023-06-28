package com.example.backendpi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Images {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(length = 1000)
 private List<String> url;
 @OneToOne
 @JoinColumn(name = "cancha_id", referencedColumnName = "id")
 @JsonIgnore
 private Cancha cancha;
 @OneToOne
 @JoinColumn( name = "categoria_id" , referencedColumnName = "id")
 @JsonIgnore
 private Categoria categoria;
}
