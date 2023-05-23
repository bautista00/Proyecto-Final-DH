package com.example.backendpi.service;

import com.example.backendpi.domain.Domicilio;
import com.example.backendpi.repository.DomicilioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {
    private DomicilioRepository domicilioRepository;

    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }


    public Domicilio guardar(Domicilio domicilio){
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
