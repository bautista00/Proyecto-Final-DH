package com.example.backendpi.service;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.repository.CanchaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanchaService {
    private CanchaRepository canchaRepository;

    public CanchaService(CanchaRepository canchaRepository) {
        this.canchaRepository = canchaRepository;
    }

    public Cancha guardar(Cancha cancha){
        return canchaRepository.save(cancha);
    }

    public Optional<Cancha> buscarXId(Long id){
        return canchaRepository.findById(id);
    }

    public void borrarXId(Long id){
        canchaRepository.deleteById(id);
    }

    public List<Cancha> buscarTodos(){
        return canchaRepository.findAll();
    }
    public Cancha actualizar(Cancha cancha){
        if (canchaRepository.findById(cancha.getId()).isPresent()){
            canchaRepository.save(cancha);
        }
        return  cancha;
    }
}
