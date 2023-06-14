package com.example.backendpi.service;

import com.example.backendpi.domain.Domicilio;
import com.example.backendpi.repository.BarrioRepository;
import com.example.backendpi.repository.DomicilioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DomicilioServiceImpl implements DomicilioService{

    private final DomicilioRepository domicilioRepository;
    private final BarrioRepository barrioRepository;

    public Domicilio guardar(Domicilio domicilio){
        if (barrioRepository.findByNombre(domicilio.getBarrio().getNombre()) != null){
            domicilio.setBarrio(barrioRepository.findByNombre(domicilio.getBarrio().getNombre()));
        }
        return domicilioRepository.save(domicilio);
    }

    public Optional<Domicilio> buscarXId(Long id){
        return domicilioRepository.findById(id);
    }

    public void borrarXId(Long id){
        domicilioRepository.deleteById(id);
    }

    public List<Domicilio> buscarTodos(){
        return domicilioRepository.findAll();
    }
    public Domicilio actualizar(Domicilio domicilio){
        if (domicilioRepository.findById(domicilio.getId()).isPresent()){
            domicilioRepository.save(domicilio);
        }
        return  domicilio;
    }
}
