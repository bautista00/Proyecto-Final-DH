package com.example.backendpi.service;

import com.amazonaws.services.backup.model.MissingParameterValueException;
import com.example.backendpi.converters.UserToClientDTOConverter;
import com.example.backendpi.converters.UserToOwnerDTOConverter;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.OwnerDTO;
import com.example.backendpi.dto.PageResponseDTO;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.dto.ClientDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.backendpi.domain.Role.ADMIN;
import static com.example.backendpi.domain.Role.USER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ConversionService conversionService;

    private final JwtService jwtService;

    private final UserToClientDTOConverter userToClientDTOConverter;

    private final UserToOwnerDTOConverter userToOwnerDTOConverter;


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
                        .role(ADMIN)
                        .build());
            } else if (signUpRequest.getCuil() == null && signUpRequest.getCuil() == null && signUpRequest.getTelefono() == null) {
                return userRepository.save(User.builder()
                        .email(signUpRequest.getUsername())
                        .password(passwordEncoder.encode(signUpRequest.getPassword()))
                        .name(signUpRequest.getNombre())
                        .apellido(signUpRequest.getApellido())
                        .role(USER)
                        .build());
            }
            else {
                throw new MissingParameterValueException("Faltan llenar algunos campos");
            }

        }
        throw new EntityExistsException();
    }

    @Override
    public PageResponseDTO<ClientDTO> getUsers(Pageable pageable) {

        Page<User> userPage = userRepository.findAll(pageable);
        return new PageResponseDTO<>(
                userPage.getContent().stream()
                        .map(user -> conversionService.convert(user, ClientDTO.class)).toList()
                , userPage.getPageable()
                , userPage.getTotalPages());
    }

    @Override
    public OwnerDTO getOwner(String token) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(jwtService.extractUserName(token));
        if(user !=null){
            OwnerDTO ownerDTO = userToOwnerDTOConverter.convert(user);
            return ownerDTO;
        }else {
            throw new ResourceNotFoundException("No se encontro el usuario");
        }
//
    }


    @Override
    public ClientDTO getClient(String token) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(jwtService.extractUserName(token));
        if(user !=null){
            ClientDTO clientDTO = userToClientDTOConverter.convert(user);
            return clientDTO;
        }else {
            throw new ResourceNotFoundException("No se encontro el usuario");
        }
//
    }


}
