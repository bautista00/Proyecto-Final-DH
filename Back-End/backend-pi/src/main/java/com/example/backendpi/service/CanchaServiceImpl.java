package com.example.backendpi.service;

import com.amazonaws.services.backup.model.MissingParameterValueException;
import com.amazonaws.services.backup.model.ResourceNotFoundException;
import com.amazonaws.services.mq.model.NotFoundException;
import com.example.backendpi.converters.CanchaDTOaCanchaConverter;
import com.example.backendpi.converters.CanchaToCanchaDTOConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Deporte;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.CanchaRepository;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CanchaServiceImpl implements CanchaService{

    private final CanchaRepository canchaRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CanchaDTOaCanchaConverter canchaDTOaCanchaConverter;

    private final CanchaToCanchaDTOConverter canchaToCanchaDTOConverter;



    @Override
    public Cancha guardar(CanchaDTO canchaDTO,String token) {
        Cancha cancha = canchaDTOaCanchaConverter.convert(canchaDTO);
        cancha.setOwner(userRepository.findByEmail(jwtService.extractUserName(token)));
        cancha.setTurnoList(new HashSet<>());
        return canchaRepository.save(cancha);
    }

    @Override
    public Optional<CanchaDTO> buscarXId(Long id) {
        Optional<Cancha> cancha  = canchaRepository.findById(id);
        if(cancha.isPresent()){
            return Optional.of(canchaToCanchaDTOConverter.convert(cancha.get()));
        }else {
            throw new NotFoundException("Hola rodo 2");
        }

    }

    @Override
    public void borrarXId(Long id) throws NotFoundException{
        if(canchaRepository.findById(id).isPresent()){
            canchaRepository.deleteById(id);
        }else {
            throw new NotFoundException("No existe rey");
        }
    }

    @Override
    public List<CanchaDTO> buscarTodos() {
        return null;
    }

    @Override
    public CanchaDTO actualizar(CanchaDTO canchaDTO) {
        if(canchaRepository.findById(canchaDTO.getId()).isPresent()){
            canchaToCanchaDTOConverter.convert(canchaRepository.save(canchaDTOaCanchaConverter.convert(canchaDTO)));
            return canchaDTO;
        }
        else {
            throw new NotFoundException("Nose");
        }
    }

    @Override
    public List<CanchaDTO> buscarXDeporte(Deporte deporte) {
        if(canchaRepository.findByDeporte(deporte).size()>0) {
            List<CanchaDTO> canchaDTOS = canchaRepository.findByDeporte(deporte);
            return canchaDTOS;
        }
        else {
            throw new NotFoundException("No existe mi rey");
        }

    }

    @Override
    public List<CanchaDTO> buscarPorOwner(String token) {
        if(canchaRepository.findByOwner(jwtService.extractUserName(token)).size()>0) {
            List<CanchaDTO> canchaDTOS = canchaRepository.findByOwner(jwtService.extractUserName(token));
            return canchaDTOS;
        }else {
            throw new NotFoundException("hola rodo");
        }
    }


}
