package com.example.backendpi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String numero;
    @ManyToOne
    @JoinColumn(name ="barrio_id",referencedColumnName = "id")
    private Barrio barrio;
    private String provincia;
    @OneToOne(mappedBy = "domicilio")
    private User user;

}
