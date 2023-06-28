package com.example.backendpi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDeleteAction;

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
 @Column(length = 3000)
 private List<String> url;
 @OneToOne(mappedBy = "images")
 @JsonIgnore
 private Cancha cancha;
 @OneToOne
 @JoinColumn( name = "categoria_id" , referencedColumnName = "id")
 @JsonIgnore
 private Categoria categoria;
}
