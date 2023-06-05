package com.example.backendpi.service;

import com.amazonaws.services.mq.model.NotFoundException;
import com.example.backendpi.converters.CanchaDTOaCanchaConverter;
import com.example.backendpi.converters.CanchaToCanchaDTOConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.exceptions.BadRequestException;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.CanchaRepository;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Optional<CanchaDTO> buscarXId(Long id) throws ResourceNotFoundException{
        Optional<Cancha> cancha  = canchaRepository.findById(id);
        if(cancha.isPresent()){
            return Optional.of(canchaToCanchaDTOConverter.convert(cancha.get()));
        }else {
            throw new ResourceNotFoundException("No existe la cancha buscada con ese id" + id);
        }

    }

    @Override
    public void borrarXId(Long id) throws ResourceNotFoundException{
        if(canchaRepository.findById(id).isPresent()){
            canchaRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("No se pudo borrar la cancha con ese id" + id);
        }
    }

    @Override
    public List<CanchaDTO> buscarTodos() {
        return null;
    }

    @Override
    public CanchaDTO actualizar(CanchaDTO canchaDTO) throws ResourceNotFoundException{
        if(canchaRepository.findById(canchaDTO.getId()).isPresent()){
            canchaToCanchaDTOConverter.convert(canchaRepository.save(canchaDTOaCanchaConverter.convert(canchaDTO)));
            return canchaDTO;
        }
        else {
            throw new ResourceNotFoundException("No se pudo actualizar correctamente la informacion");
        }
    }

    @Override
    public List<CanchaDTO> buscarXCategoria(Categoria categoria)throws ResourceNotFoundException {
        if(canchaRepository.findByCategoria(categoria).size()>0) {
            List<CanchaDTO> canchaDTOS = canchaRepository.findByCategoria(categoria);
            return canchaDTOS;
        }
        else {
            throw new ResourceNotFoundException("No existe la categoria buscadad");
        }

    }

    @Override
    public List<CanchaDTO> buscarPorOwner(String token) throws ResourceNotFoundException {
        if(canchaRepository.findByOwner(jwtService.extractUserName(token)).size()>0) {
            List<CanchaDTO> canchaDTOS = canchaRepository.findByOwner(jwtService.extractUserName(token));
            return canchaDTOS;
        }else {
            throw new ResourceNotFoundException("No existen las canchas buscadas por el propietario");
        }
    }


}
