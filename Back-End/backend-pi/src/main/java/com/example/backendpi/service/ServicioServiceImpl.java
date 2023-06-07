package com.example.backendpi.service;

import com.amazonaws.services.mq.model.NotFoundException;
import com.example.backendpi.domain.Servicio;
import com.example.backendpi.repository.ServicioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioServiceImpl implements ServicioService{

    private final ServicioRepository servicioRepository;

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Optional<Servicio> buscarXId(Long id) {
        Optional<Servicio> optionalServicio= servicioRepository.findById(id);
        if(optionalServicio.isPresent()){
            return optionalServicio;
        }else {
            throw new EntityNotFoundException("No existe el servicio con ese id numero:" + id);
        }
    }

    @Override
    public List<Servicio> buscarTodos() {
       if(servicioRepository.findAll().size()>0){
          return servicioRepository.findAll();
       }
       else{
           throw new NotFoundException("No existe la lista de servicios deseada");
       }
    }

    @Override
    public void borrarXId(Long id) {
        Optional<Servicio> optionalServicio= servicioRepository.findById(id);
        if(optionalServicio.isPresent()){
            servicioRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("No existe el servicio con ese " + id);
        }
    }

    @Override
    public Servicio actualizar(Servicio servicio) {
        Optional<Servicio> optionalServicio= servicioRepository.findById(servicio.getId());
        if(optionalServicio.isPresent()){
            return servicioRepository.save(servicio);
        }else {
            throw new EntityNotFoundException("No existe el servicio con ese id: " + servicio.getId());
        }
    }

}
