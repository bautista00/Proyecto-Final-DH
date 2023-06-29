package com.example.backendpi.service;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Valoracion;
import com.example.backendpi.dto.ValoracionDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ValoracionService {

    ValoracionDTO agregarValoracion(ValoracionDTO valoracionDTO,String token, Long id)throws ResourceNotFoundException;

    List<ValoracionDTO> listarValoracionPorCancha(Cancha cancha)throws ResourceNotFoundException;

    ValoracionDTO modificarValoracion(ValoracionDTO valoracionDTO)throws ResourceNotFoundException;

    void eliminarValoracion(Long id) throws ResourceNotFoundException;

}
