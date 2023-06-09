package com.example.backendpi.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
 private String url;
 @ManyToOne
 @JoinColumn(name = "cancha_id", referencedColumnName = "id")
 private Cancha cancha;
}
