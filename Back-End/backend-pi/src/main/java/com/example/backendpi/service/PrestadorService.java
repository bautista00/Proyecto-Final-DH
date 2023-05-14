package com.example.backendpi.service;

import com.example.backendpi.domain.Cliente;
import com.example.backendpi.domain.Prestador;
import com.example.backendpi.repository.PrestadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestadorService {
    private PrestadorRepository prestadorRepository;

    public PrestadorService(PrestadorRepository prestadorRepository) {
        this.prestadorRepository = prestadorRepository;
    }

    public Prestador guardar(Prestador prestador){
        return prestadorRepository.save(prestador);
    }

    public Optional<Prestador> buscarXId(Long id){
        return prestadorRepository.findById(id);
    }

    public void borrarXId(Long id){
        prestadorRepository.deleteById(id);
    }

    public List<Prestador> buscarTodos(){
        return prestadorRepository.findAll();
    }
    public Prestador actualizar(Prestador prestador){
        if (prestadorRepository.findById(prestador.getId()).isPresent()){
            prestadorRepository.save(prestador);
        }
        return  prestador;
    }
}
