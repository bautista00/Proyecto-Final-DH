package com.example.backendpi.service;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.UserDTO;
import com.example.backendpi.dto.PageResponseDTO;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDetails createUser(SignUpRequest signUpRequest);

//    PageResponseDTO<ClientDTO> getUsers(Pageable pageable);

    void borrarCliente(Long id) throws ResourceNotFoundException;

    List<User> listarTodos() throws ResourceNotFoundException;

    UserDTO getUser(String token) throws ResourceNotFoundException;


    List<CanchaDTO> listarCanchasFav(String token) throws ResourceNotFoundException;


    void modificarUser(UserDTO userDTO,String token) throws ResourceNotFoundException;

}
