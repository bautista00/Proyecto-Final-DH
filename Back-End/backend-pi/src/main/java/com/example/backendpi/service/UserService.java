package com.example.backendpi.service;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.OwnerDTO;
import com.example.backendpi.dto.PageResponseDTO;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.dto.ClientDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetails createUser(SignUpRequest signUpRequest);

    PageResponseDTO<ClientDTO> getUsers(Pageable pageable);

    OwnerDTO getOwner(String token)throws ResourceNotFoundException;

    ClientDTO getClient(String token)throws ResourceNotFoundException;




}
