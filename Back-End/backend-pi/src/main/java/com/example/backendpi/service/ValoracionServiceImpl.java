package com.example.backendpi.service;

import com.example.backendpi.converters.ValoracionDTOToValoracionConverter;
import com.example.backendpi.converters.ValoracionToValoracionDTOConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.User;
import com.example.backendpi.domain.Valoracion;
import com.example.backendpi.dto.ValoracionDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.CanchaRepository;
import com.example.backendpi.repository.UserRepository;
import com.example.backendpi.repository.ValoracionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class ValoracionServiceImpl implements ValoracionService{

    private final UserRepository userRepository;
    private final ValoracionRepository valoracionRepository;

    private final CanchaRepository canchaRepository;

    private final ValoracionToValoracionDTOConverter vToVDTOConverter;

    private final JwtService jwtService;

    private final ValoracionDTOToValoracionConverter vDTOToVConverter;

    @Override
    public ValoracionDTO agregarValoracion(ValoracionDTO valoracionDTO, String token,Long id) throws ResourceNotFoundException {
        Cancha cancha = canchaRepository.findById(id).get();

        String userName = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(userName);


        if (valoracionRepository.findByUserAndCancha(user, cancha) != null) {
            throw new ResourceNotFoundException("El usuario ya ha dejado una valoración para esta cancha");
        }
        valoracionDTO.setUserName(user.getName());
        valoracionDTO.setUserID(user.getId());
        valoracionDTO.setApellido(user.getApellido());
        valoracionDTO.setCanchaID(id);
        Valoracion valoracion = vDTOToVConverter.convert(valoracionDTO);
        valoracion.setUser(user);
        valoracion.setCancha(cancha);

        Valoracion savedValoracion = valoracionRepository.save(valoracion);

        return vToVDTOConverter.convert(savedValoracion);
    }



    @Override
    public List<ValoracionDTO> listarValoracionPorCancha(Cancha cancha) throws ResourceNotFoundException {
        List<Valoracion> valoracionList = valoracionRepository.findByCancha(cancha);
        List<ValoracionDTO> valoracionDTOS = new ArrayList<>();
        if (valoracionList.size()>0){
            for (Valoracion valoracion : valoracionList) {
                valoracionDTOS.add(vToVDTOConverter.convert(valoracion));
            }
            return valoracionDTOS;
        }
        throw new ResourceNotFoundException("La cancha: " + cancha.getNombre() + " no tiene valoraciones aun");
    }

    @Override
    public ValoracionDTO modificarValoracion(ValoracionDTO valoracionDTO) throws ResourceNotFoundException {
        Valoracion valoracion = valoracionRepository.findByUser(vDTOToVConverter.convert(valoracionDTO).getUser());
        if(valoracion!=null){
            return vToVDTOConverter.convert(valoracionRepository.save(valoracion));
        }
        throw new ResourceNotFoundException("El usuario: " + valoracion.getUser().getName()+" no a hecho ningun valoracion todavia");
    }

    @Override
    public void eliminarValoracion(Long id) throws ResourceNotFoundException {
        if (valoracionRepository.findById(id).isPresent()){
            valoracionRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("No existe una valoracion con ese id");
    }
}
