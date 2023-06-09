package com.example.backendpi.service;

import com.example.backendpi.domain.Barrio;

import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.repository.BarrioRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BarrioServiceImpl implements BarrioService{
    private final BarrioRepository barrioRepository;

    @Override
    public Barrio guardar(Barrio barrio) throws EntityExistsException {
        if (barrioRepository.findByNombre(barrio.getNombre()) == null) {
            return barrioRepository.save(barrio);
        }
        throw new EntityExistsException("El barrio que quiere guardar ya existe") ;
    }

    @Override
    public List<Barrio> buscarTodos() throws ResourceNotFoundException {
        if (barrioRepository.findAll().size() > 0){
            return barrioRepository.findAll();
        }
          throw new ResourceNotFoundException("No se encontraron barrios") ;
    }

    @Override
    public void borrarXId(Long id) throws ResourceNotFoundException {
        if(barrioRepository.findById(id).isPresent()){
            barrioRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No se encontro el barrio con el id" + id);
        }
    }
}
