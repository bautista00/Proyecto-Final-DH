package com.example.backendpi.service;

import com.example.backendpi.domain.Turno;
import com.example.backendpi.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno guardar(Turno turno){
        return turnoRepository.save(turno);
    }

    public Optional<Turno> buscarXId(Long id){
        return turnoRepository.findById(id);
    }

    public void borrarXId(Long id){
        turnoRepository.deleteById(id);
    }

    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
    }
    public Turno actualizar(Turno turno){
        if (turnoRepository.findById(turno.getId()).isPresent()){
            turnoRepository.save(turno);
        }
        return  turno;
    }
}
