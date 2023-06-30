package com.example.backendpi.service;

import com.amazonaws.services.backup.model.MissingParameterValueException;
import com.example.backendpi.converters.CanchaToCanchaDTOConverter;
import com.example.backendpi.converters.UserDTOToUserConverter;
import com.example.backendpi.converters.UserToUserDTOConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.CanchasFavoritas;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.UserDTO;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.backendpi.domain.Role.OWNER;
import static com.example.backendpi.domain.Role.USER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ConversionService conversionService;

    private final JwtService jwtService;

    private final UserToUserDTOConverter userToUserDTOConverter;

    private final UserDTOToUserConverter userDTOToUserConverter;

    private final CanchaToCanchaDTOConverter converter;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.getFirstByEmail(userName);
        if (userDetails ==null){
            throw new UsernameNotFoundException(userName);
        }
        return userDetails;
    }

    @Override
    public UserDetails createUser(SignUpRequest signUpRequest) {
        if(userRepository.findByEmail(signUpRequest.getUsername())==null) {

            if (signUpRequest.getCbu() != null && signUpRequest.getCuil() != null && signUpRequest.getTelefono() != null) {
                return userRepository.save(User.builder()
                        .email(signUpRequest.getUsername())
                        .password(passwordEncoder.encode(signUpRequest.getPassword()))
                        .name(signUpRequest.getNombre())
                        .apellido(signUpRequest.getApellido())
                        .cbu(signUpRequest.getCbu())
                        .cuil(signUpRequest.getCuil())
                        .telefono(signUpRequest.getTelefono())
                        .role(OWNER)
                        .tokenEmail(UUID.randomUUID().toString())
                        .verified(false)
                        .build());
            } else if (signUpRequest.getCuil() == null && signUpRequest.getCuil() == null && signUpRequest.getTelefono() == null) {
                return userRepository.save(User.builder()
                        .email(signUpRequest.getUsername())
                        .password(passwordEncoder.encode(signUpRequest.getPassword()))
                        .name(signUpRequest.getNombre())
                        .apellido(signUpRequest.getApellido())
                        .role(USER)
                        .tokenEmail(UUID.randomUUID().toString())
                        .verified(false)
                        .build());
            }
            else {
                throw new MissingParameterValueException("Faltan llenar algunos campos");
            }

        }
        throw new EntityExistsException();
    }


    public void borrarCliente(Long id) throws ResourceNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("No se encontro el usuario");
    }

    @Override
    public List<User> listarTodos() throws ResourceNotFoundException {
        List<User> userList = userRepository.findAll();
        if(userList.size()>0){
            return userList;
        }
        throw new ResourceNotFoundException("No existe la lista");
    }

    @Override
    public UserDTO getUser(String token) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(jwtService.extractUserName(token));
        if(user !=null){
            return userToUserDTOConverter.convert(user);
        }else {
            throw new ResourceNotFoundException("No se encontro el usuario");
        }
//
    }

    @Override
    public List<CanchaDTO> listarCanchasFav(String token) throws ResourceNotFoundException {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email);
        CanchasFavoritas favoritesList = user.getCanchasFavoritas();

        if (favoritesList != null) {
            List<Cancha> canchaList = favoritesList.getCanchas();
            List<CanchaDTO> canchaDTOS = new ArrayList<>();

            if (!canchaList.isEmpty()) {
                for (Cancha cancha : canchaList) {
                    canchaDTOS.add(converter.convert(cancha));
                }
                return canchaDTOS;
            } else {
                throw new ResourceNotFoundException("La lista de favoritos está vacía");
            }
        } else {
            throw new ResourceNotFoundException("La lista de favoritos no existe");
        }
    }



    @Override
    public void modificarUser(UserDTO userDTO,String token) throws ResourceNotFoundException {
        User user = userDTOToUserConverter.convert(userDTO);
        String email = jwtService.extractUserName(token);
        User existingUser = userRepository.findByEmail(email);

        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getNombre() != null) {
            existingUser.setName(userDTO.getNombre());
        }

        if (userDTO.getApellido() != null) {
            existingUser.setApellido(userDTO.getApellido());
        }
        if (userDTO.getCuil() != null) {
            existingUser.setCuil(userDTO.getCuil());
        }
        if (userDTO.getCBU() != null) {
            existingUser.setCbu(userDTO.getCBU());
        }
        if (userDTO.getTelefono() != null) {
            existingUser.setTelefono(userDTO.getTelefono());
        }

        if (userDTO.getRole() != null) {
            existingUser.setRole(userDTO.getRole());
        }



        userRepository.save(existingUser);
    }




}
