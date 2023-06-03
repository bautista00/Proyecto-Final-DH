package com.example.backendpi.service;

import com.example.backendpi.converters.CanchaDTOaCanchaConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.CanchaRepository;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CanchaServiceImpl implements CanchaService{

    private final CanchaRepository canchaRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CanchaDTOaCanchaConverter canchaDTOaCanchaConverter;


    @Override
    public Cancha guardar(CanchaDTO canchaDTO,String token) {
        Cancha cancha = canchaDTOaCanchaConverter.convert(canchaDTO);
        cancha.setOwner(userRepository.findByEmail(jwtService.extractUserName(token)));
        return canchaRepository.save(cancha);
    }

    @Override
    public Optional<CanchaDTO> buscarXId(Long id) {
        return Optional.empty();
    }

    @Override
    public void borrarXId(Long id) {

    }

    @Override
    public List<CanchaDTO> buscarTodos() {
        return null;
    }

    @Override
    public CanchaDTO actualizar(CanchaDTO canchaDTO) {
        return null;
    }
}
